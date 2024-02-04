package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.UsuarioCompletoRelatorioDTO;
import VS13.Squad09.EduMatch.dtos.UsuarioECertificadoRelatorioDTO;
import VS13.Squad09.EduMatch.dtos.request.LoginCreateDTO;
import VS13.Squad09.EduMatch.dtos.request.UsuarioCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.PessoaJuridicaDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.entities.Ranking;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Elo;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.TipoEmpresa;
import VS13.Squad09.EduMatch.entities.enums.TipoUsuario;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.exceptions.RegraDeNegocioException;
import VS13.Squad09.EduMatch.repositories.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final ObjectMapper objectMapper;
    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;
    private final RankingService rankingService;

    public UsuarioDTO criar(UsuarioCreateDTO usuarioCreateDTO) throws Exception {
        log.info("Criando usuario");
        validarUsuario(usuarioCreateDTO);

        Usuario usuarioEntity = objectMapper.convertValue(usuarioCreateDTO, Usuario.class);
        usuarioEntity.setStatus(Status.ATIVO);
        usuarioEntity.setPontuacao(0);
        usuarioEntity.setMoedas(0);
        usuarioEntity.setElo(Elo.FERRO);
        if (usuarioEntity.getTipoUsuario() == TipoUsuario.PESSOA_FISICA) {
            usuarioEntity.setTipoEmpresa(TipoEmpresa.USUARIO_PADRAO);
        }

        String senha = hashPassword(usuarioEntity.getSenha());
        usuarioEntity.setSenha(senha);
        usuarioRepository.save(usuarioEntity);

        UsuarioDTO usuarioDTO2 = objectMapper.convertValue(usuarioEntity, UsuarioDTO.class);
        //emailService.sendEmail(usuarioEntity, null, 1);
        return usuarioDTO2;
    }

    public List<UsuarioDTO> listarTodos() throws BancoDeDadosException {
        return usuarioRepository.findAll().stream()
                .map(usuario -> objectMapper.convertValue(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    public List<UsuarioDTO> listarPorStatus(Integer status) throws BancoDeDadosException {
        return listarTodos().stream()
                .filter(usuarioDTO -> usuarioDTO.getStatus().getTipo().equals(status))
                .collect(Collectors.toList());
    }

    public UsuarioDTO listarPorId(Integer id) throws Exception {
        return objectMapper.convertValue(usuarioRepository.findById(id), UsuarioDTO.class);
    }

    public UsuarioDTO atualizar(UsuarioCreateDTO usuarioCreateDTO) throws Exception {
        validarUsuario(usuarioCreateDTO);
        Usuario usuarioAtualizado = objectMapper.convertValue(usuarioCreateDTO, Usuario.class);
        usuarioRepository.save(usuarioAtualizado);
        return objectMapper.convertValue(usuarioAtualizado, UsuarioDTO.class);
        //emailService.sendEmail(usuario, 2);
    }

    public UsuarioDTO listarPorEmail(String email) throws BancoDeDadosException {
        return objectMapper.convertValue(usuarioRepository.listarPorEmail(email), UsuarioDTO.class);
    }

    public List<UsuarioDTO> rankearUsuarios() throws BancoDeDadosException {
        return usuarioRepository.rankearJogadores().stream().map(usuario ->
                objectMapper.convertValue(usuario, UsuarioDTO.class)).collect(Collectors.toList());
    }

    public UsuarioDTO atualizar(Integer id, UsuarioCreateDTO usuarioCreateDTO) throws Exception {

        validarUsuario(usuarioCreateDTO);
        Usuario usuarioRecuperado = usuarioRepository.findById(id).get();
        BeanUtils.copyProperties(usuarioCreateDTO, usuarioRecuperado);

        Integer eloAtual = usuarioRecuperado.getElo().ordinal();

        if (eloAtual < Elo.values().length) {
            String elo = Elo.valueOf(++eloAtual).name();
            Ranking ranking = rankingService.subirRanking(elo, usuarioRecuperado);
            if (ranking != null) {
                usuarioRecuperado.setRanking(ranking);
                usuarioRecuperado.setElo(Elo.valueOf(++eloAtual));
            }
        }

        usuarioRepository.save(usuarioRecuperado);
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        BeanUtils.copyProperties(usuarioRecuperado, usuarioDTO);
        return usuarioDTO;
    }

    public Boolean login(LoginCreateDTO loginCreateDTO) throws Exception {
        Usuario usuarioProcurado = usuarioRepository.listarPorEmail(loginCreateDTO.getEmail());
        log.info(usuarioProcurado.toString());
        if (hashPassword(loginCreateDTO.getSenha()).equals(usuarioProcurado.getSenha())) {
            return true;
        }
        throw new IllegalArgumentException("Senha inválida.");
    }

    public List<PessoaJuridicaDTO> listarEmpresas() throws Exception {
        return usuarioRepository.findAll().stream()
                .filter(usuario -> usuario.getTipoUsuario().ordinal() == 1)
                .filter(usuario -> usuario.getStatus().ordinal() == 1)
                .map(usuario -> objectMapper.convertValue(usuario, PessoaJuridicaDTO.class))
                .collect(Collectors.toList());
    }

    public UsuarioDTO delete(Integer id) throws Exception {
        Usuario usuarioProcurado = usuarioRepository.findById(id).orElseThrow(() -> new IllegalStateException("O valor está ausente!"));
        usuarioProcurado.setStatus(Status.INATIVO);
        String email = usuarioProcurado.getEmail();
        usuarioProcurado.setEmail(null);
        usuarioRepository.save(usuarioProcurado);
        usuarioProcurado.setEmail(email);
        UsuarioDTO usuarioDTO = objectMapper.convertValue(usuarioProcurado, UsuarioDTO.class);
        emailService.sendEmail(usuarioProcurado, null, 3);
        return usuarioDTO;
    }


    //METODOS ADICIONAIS

    public void validarUsuario(UsuarioCreateDTO usuarioCreateDTO) throws RegraDeNegocioException {
        if (usuarioCreateDTO.getCNPJ() == null && usuarioCreateDTO.getCPF() == null) {
            throw new RegraDeNegocioException("Documentação vazia");
        }
    }

    private String hashPassword(String senha) {
        try {
            MessageDigest cript = MessageDigest.getInstance("SHA-256");
            byte[] passwordBytes = senha.getBytes(StandardCharsets.UTF_8);
            byte[] hashedBytes = cript.digest(passwordBytes);

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public UsuarioCompletoRelatorioDTO listarUsuarioCompletoRelatorio(Integer id) {
        UsuarioCompletoRelatorioDTO usuarioCompletoRelatorioDTO = usuarioRepository.procurarUsuarioCompletoDTO(id);
        usuarioCompletoRelatorioDTO.setContatoUsuario(usuarioRepository.procurarContatos(usuarioCompletoRelatorioDTO.getIdUsuario()));
        usuarioCompletoRelatorioDTO.setEnderecoUsuario(usuarioRepository.procurarEnderecos(usuarioCompletoRelatorioDTO.getIdUsuario()));
        return usuarioCompletoRelatorioDTO;
    }

    public UsuarioECertificadoRelatorioDTO listarUsuarioComCertificado(Integer id) {
        UsuarioECertificadoRelatorioDTO usuarioECertificadoRelatorioDTO = usuarioRepository.procurarUsuarioECertificadoDTO(id);
        usuarioECertificadoRelatorioDTO.setCertificadoUsuario(usuarioRepository.procurarCertificado(id));
        return usuarioECertificadoRelatorioDTO;
    }

    public Page<Usuario> listPaginadaByName(Integer paginaSolicitada, Integer tamanhoPagina) {
        Pageable pageable = PageRequest.of(paginaSolicitada, tamanhoPagina, Sort.by("nome").descending());
        return usuarioRepository.findAll(pageable);
    }
}



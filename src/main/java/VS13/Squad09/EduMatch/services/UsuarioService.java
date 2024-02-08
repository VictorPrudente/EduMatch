package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.UsuarioCompletoRelatorioDTO;
import VS13.Squad09.EduMatch.dtos.UsuarioECertificadoRelatorioDTO;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO criar(UsuarioCreateDTO usuarioCreateDTO) throws RegraDeNegocioException {
        log.info("Criando usuario");
            validarCredencialUsuario(usuarioCreateDTO);
            Usuario usuarioEntity = objectMapper.convertValue(usuarioCreateDTO, Usuario.class);

            String senhaEncriptografada = passwordEncoder.encode(usuarioEntity.getSenha());
            usuarioEntity.setSenha(senhaEncriptografada);

            usuarioEntity.setTipoUsuario(validarTipoUsuario(usuarioCreateDTO));
            usuarioEntity.setStatus(Status.ATIVO);
            usuarioEntity.setPontuacao(0);
            usuarioEntity.setMoedas(0);
            usuarioEntity.setElo(Elo.FERRO);
            usuarioEntity.setRanking(rankingService.rankingInicial());
            if (usuarioEntity.getTipoUsuario() == TipoUsuario.PESSOA_FISICA) {
                usuarioEntity.setTipoEmpresa(TipoEmpresa.USUARIO_PADRAO);
            }
            usuarioRepository.save(usuarioEntity);

            //emailService.sendEmail(usuarioEntity, null, 1);
            return objectMapper.convertValue(usuarioEntity, UsuarioDTO.class);
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

    public UsuarioDTO listarPorEmail(String email) throws BancoDeDadosException {
        return objectMapper.convertValue(usuarioRepository.listarPorEmail(email), UsuarioDTO.class);
    }

    public UsuarioDTO atualizar(Integer id, UsuarioCreateDTO usuarioCreateDTO) throws Exception {

        validarCredencialUsuario(usuarioCreateDTO);
        Usuario usuarioRecuperado = usuarioRepository.findById(id).get();
        BeanUtils.copyProperties(usuarioCreateDTO, usuarioRecuperado);

        Integer eloAtual = usuarioRecuperado.getElo().ordinal();
        Integer proximoelo = eloAtual + 1;
        if (eloAtual < Elo.values().length) {
            String elo = Elo.valueOf(proximoelo).name();
            Ranking ranking = rankingService.subirRanking(elo, usuarioRecuperado);
            if (ranking != null) {
                usuarioRecuperado.setRanking(ranking);
                usuarioRecuperado.setElo(Elo.valueOf(proximoelo));
            }
        }

        usuarioRepository.save(usuarioRecuperado);
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        BeanUtils.copyProperties(usuarioRecuperado, usuarioDTO);
        return usuarioDTO;
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

    private void validarCredencialUsuario(UsuarioCreateDTO usuarioCreateDTO) throws RegraDeNegocioException {
        if (usuarioCreateDTO.getCNPJ() == null && usuarioCreateDTO.getCPF() == null) {
            throw new RegraDeNegocioException("Documentação vazia");
        }
    }

    private TipoUsuario validarTipoUsuario(UsuarioCreateDTO usuarioCreateDTO) throws RegraDeNegocioException {
        if (usuarioCreateDTO.getCNPJ() != null){
            return TipoUsuario.PESSOA_JURIDICA;
        }
        if (usuarioCreateDTO.getCPF() != null) {
            return TipoUsuario.PESSOA_FISICA;
        }
        throw new RegraDeNegocioException("Usuário inválido");
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

    public Optional<Usuario> findByEmailAndSenha(String login, String senha){
        return usuarioRepository.findByEmailAndSenha(login, senha);
    }

    public Usuario findById(Integer idUsuario) throws RegraDeNegocioException {
        return usuarioRepository.findById(idUsuario).orElseThrow(() -> new RegraDeNegocioException("Login não encontrado"));
    }

    public Optional<Usuario> findByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    public Integer getIdLoggedUser(){
        return Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }

    public Usuario getLoggedUser() throws RegraDeNegocioException {
        return findById(getIdLoggedUser());
    }
}



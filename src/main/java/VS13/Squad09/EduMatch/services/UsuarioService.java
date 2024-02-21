package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.UsuarioCompletoRelatorioDTO;
import VS13.Squad09.EduMatch.dtos.UsuarioECertificadoRelatorioDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.dtos.request.UsuarioCreateDTO;
import VS13.Squad09.EduMatch.entities.Contato;
import VS13.Squad09.EduMatch.entities.Endereco;
import VS13.Squad09.EduMatch.entities.Ranking;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Elo;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.TipoUsuario;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import VS13.Squad09.EduMatch.exceptions.RegraDeNegocioException;
import VS13.Squad09.EduMatch.repositories.CargoRepository;
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
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
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
    private final CargoRepository cargoRepository;

    public UsuarioDTO criar(UsuarioCreateDTO usuarioCreateDTO) throws RegraDeNegocioException {
        log.info("Criando usuario");

            validarCredencialUsuario(usuarioCreateDTO);
            Usuario usuarioEntity = objectMapper.convertValue(usuarioCreateDTO, Usuario.class);

            SCryptPasswordEncoder sCryptPasswordEncoder = new SCryptPasswordEncoder();
            String senhaEncriptografada = sCryptPasswordEncoder.encode(usuarioEntity.getSenha());
            usuarioEntity.setSenha(senhaEncriptografada);

            usuarioEntity.setTipoUsuario(validarTipoUsuario(usuarioCreateDTO));
            usuarioEntity.setStatus(Status.ATIVO);

            if (usuarioEntity.getTipoUsuario().equals(TipoUsuario.PESSOA_FISICA)) {
                usuarioEntity.getCargos().add(cargoRepository.findByNome("ROLE_USUARIO"));
                usuarioEntity.setPontuacao(0);
                usuarioEntity.setMoedas(0);
                usuarioEntity.setElo(Elo.FERRO);
                usuarioEntity.setRanking(rankingService.novoRanking("FERRO"));
            }

            if (usuarioEntity.getTipoUsuario().equals(TipoUsuario.PESSOA_JURIDICA)){
                usuarioEntity.getCargos().add(cargoRepository.findByNome("ROLE_COMPANY"));
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

    public UsuarioDTO atualizar(Integer id, UsuarioCreateDTO usuarioCreateDTO) throws Exception {
        validarCredencialUsuario(usuarioCreateDTO);
        Usuario usuarioRecuperado = findById(id);
        BeanUtils.copyProperties(usuarioCreateDTO, usuarioRecuperado);

        if(usuarioRecuperado.getTipoUsuario() == TipoUsuario.PESSOA_FISICA) {
            subirElo(usuarioRecuperado);
        }

        usuarioRepository.save(usuarioRecuperado);
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        BeanUtils.copyProperties(usuarioRecuperado, usuarioDTO);
        return usuarioDTO;
    }

    public Set<UsuarioDTO> listarEmpresas() {
        return usuarioRepository.listarEmpresas();
    }

    public UsuarioDTO delete(Integer id) throws Exception {
        Usuario usuarioProcurado = usuarioRepository.findById(id).orElseThrow(() -> new IllegalStateException("O valor está ausente!"));
        usuarioProcurado.setStatus(Status.INATIVO);
        String email = usuarioProcurado.getEmail();
        usuarioProcurado.setEmail(null);
        String cpf = usuarioProcurado.getCPF();
        usuarioProcurado.setCPF(null);
        usuarioRepository.save(usuarioProcurado);
        usuarioProcurado.setEmail(email);
        UsuarioDTO usuarioDTO = objectMapper.convertValue(usuarioProcurado, UsuarioDTO.class);
        //emailService.sendEmail(usuarioProcurado, null, 3);
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

    private void subirElo(Usuario usuario) throws NaoEncontradoException {
        if (usuario.hasNextElo()) {
            Ranking proximoRanking = getNextElo(usuario);
            if (usuario.getPontuacao() >= proximoRanking.getPontuacaoNecessaria()) {
                updateRank(usuario, proximoRanking);
                if (usuario.hasNextElo()) {
                    Ranking rankingFuturo = getNextElo(usuario);
                    setPontuacao(usuario, rankingFuturo);
                }
                else {
                    usuario.setPontuacaoProximoElo(0);
                }
            }
            else {
                setPontuacao(usuario, proximoRanking);
            }
        }
    }

    private Ranking getNextElo(Usuario usuario) throws NaoEncontradoException {
        String elo = Elo.valueOf(usuario.getElo().ordinal() + 1).name();
        return rankingService.novoRanking(elo);
    }

    private void updateRank(Usuario usuario, Ranking ranking) throws NaoEncontradoException {
        Integer proximoElo = usuario.getElo().ordinal() + 1;
        usuario.setElo(Elo.valueOf(proximoElo));
        usuario.setRanking(ranking);
    }

    private void setPontuacao(Usuario usuario, Ranking ranking){
        Integer pontuacaoProximoElo = ranking.getPontuacaoNecessaria() - usuario.getPontuacao();
        usuario.setPontuacaoProximoElo(pontuacaoProximoElo);
    }

    public UsuarioDTO getById(Integer id) throws RegraDeNegocioException {
        return objectMapper.convertValue(findById(id), UsuarioDTO.class);
    }

    public void usuarioComContato(Usuario usuario, Contato contato){
        usuario.setContato(contato);
        usuarioRepository.save(usuario);
    }

    public void usuarioSemContato(Integer idUsuario){
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        usuario.setContato(null);
        usuarioRepository.save(usuario);
    }

    public void usuarioComEndereco(Usuario usuario, Endereco endereco){
        usuario.setEndereco(endereco);
        usuarioRepository.save(usuario);
    }

    public void usuarioSemEndereco(Integer idUsuario){
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        usuario.setEndereco(null);
        usuarioRepository.save(usuario);
    }
}



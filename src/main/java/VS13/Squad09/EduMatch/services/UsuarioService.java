package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.request.LoginCreateDTO;
import VS13.Squad09.EduMatch.dtos.request.UsuarioCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.TipoEmpresa;
import VS13.Squad09.EduMatch.entities.enums.TipoUsuario;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.exceptions.RegraDeNegocioException;
import VS13.Squad09.EduMatch.repositories.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public UsuarioDTO adicionar(UsuarioCreateDTO usuarioCreateDTO) throws Exception {
        log.info("Criando usuario");
        validarUsuario(usuarioCreateDTO);

        Usuario usuarioEntity = objectMapper.convertValue(usuarioCreateDTO, Usuario.class);
        usuarioEntity.setStatus(Status.ATIVO);
        usuarioEntity.setPontuacao(0);
        usuarioEntity.setMoedas(0);
        if(usuarioEntity.getTipoUsuario() == TipoUsuario.PESSOA_FISICA){
            usuarioEntity.setTipoEmpresa(TipoEmpresa.USUARIO_PADRAO);
        }
        String senha = hashPassword(usuarioEntity.getSenha());
        usuarioEntity.setSenha(senha);
        usuarioRepository.adicionar(usuarioEntity);

        UsuarioDTO usuarioDTO2 = objectMapper.convertValue(usuarioEntity, UsuarioDTO.class);
        //emailService.sendEmail(usuarioEntity, 1);

        return usuarioDTO2;
    }

    public List<UsuarioDTO> listarTodos() throws BancoDeDadosException {
        return usuarioRepository.listarTodos().stream()
                .map(usuario -> objectMapper.convertValue(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    public List<UsuarioDTO> listarPorStatus(Integer status) throws BancoDeDadosException {
        return listarTodos().stream()
                .filter(usuarioDTO -> usuarioDTO.getStatus().getTipo().equals(status))
                .collect(Collectors.toList());
    }

    public UsuarioDTO listarPorId(Integer id) throws Exception {
        return objectMapper.convertValue(usuarioRepository.listarPorId(id), UsuarioDTO.class);
    }

    public UsuarioDTO atualizar(Integer id, UsuarioCreateDTO usuarioCreateDTO) throws Exception {

        validarUsuario(usuarioCreateDTO);
        Usuario usuarioAtualizado = objectMapper.convertValue(usuarioCreateDTO, Usuario.class);
        usuarioRepository.atualizar(id, usuarioAtualizado);
        return objectMapper.convertValue(usuarioAtualizado, UsuarioDTO.class);
        //emailService.sendEmail(usuario, 2);

    }

    public UsuarioDTO listarPorEmail(String email) throws BancoDeDadosException {
            return objectMapper.convertValue(usuarioRepository.listarPorEmail(email), UsuarioDTO.class );
    }

    public List<UsuarioDTO> rankearUsuarios() throws BancoDeDadosException {
        return usuarioRepository.rankearJogadores().stream().map(usuario ->
                objectMapper.convertValue(usuario, UsuarioDTO.class)).collect(Collectors.toList());
    }

    public Boolean login(LoginCreateDTO loginCreateDTO) throws Exception {
        Usuario usuarioProcurado = usuarioRepository.listarPorEmail(loginCreateDTO.getEmail());
        log.info(usuarioProcurado.toString());
        if(hashPassword(loginCreateDTO.getSenha()).equals(usuarioProcurado.getSenha())) {
            return true;
        }
        throw new IllegalArgumentException("Senha inválida.");
    }


    public List<UsuarioDTO> listarEmpresas() throws Exception {
        return usuarioRepository.listarTodos().stream()
                .filter(usuario -> usuario.getTipoUsuario().ordinal() == 1)
                .filter(usuario -> usuario.getStatus().ordinal() == 1)
                .map(usuario -> objectMapper.convertValue(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    public UsuarioDTO delete(Integer id) throws Exception {
        Usuario usuarioProcurado = usuarioRepository.listarPorId(id);
        usuarioProcurado.setStatus(Status.INATIVO);
        usuarioRepository.atualizar(id, usuarioProcurado);
        return objectMapper.convertValue(usuarioProcurado, UsuarioDTO.class);
    }


    public UsuarioCreateDTO validarUsuario(UsuarioCreateDTO usuarioDTO) throws RegraDeNegocioException {
        if (usuarioDTO.getCNPJ() == null && usuarioDTO.getCPF() == null) {
            throw new RegraDeNegocioException("Documentação vazia");
        }
        return usuarioDTO;
    }


    private String hashPassword(String senha){
        try {
            MessageDigest cript = MessageDigest.getInstance("SHA-256");
            byte[] passwordBytes = senha.getBytes(StandardCharsets.UTF_8);
            byte[] hashedBytes = cript.digest(passwordBytes);

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes){
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }


}


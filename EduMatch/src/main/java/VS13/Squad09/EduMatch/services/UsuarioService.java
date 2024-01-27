package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.request.UsuarioCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.exceptions.RegraDeNegocioException;
import VS13.Squad09.EduMatch.repositories.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public UsuarioDTO adicionar(UsuarioCreateDTO usuarioDTO) throws Exception {
        log.info("Criando usuario");
        if (usuarioDTO.getCNPJ().isBlank() && usuarioDTO.getCPF().isBlank()) {
            throw new RegraDeNegocioException("Documentação vazia");
        }

        Usuario usuarioEntity = objectMapper.convertValue(usuarioDTO, Usuario.class);
        usuarioEntity.setStatus(Status.Ativo);
        usuarioRepository.adicionar(usuarioEntity);

        UsuarioDTO usuarioDTO2 = objectMapper.convertValue(usuarioEntity, UsuarioDTO.class);
        emailService.sendEmail(usuarioEntity, 1);

        return usuarioDTO2;
    }

    public List<UsuarioDTO> listarTodos() throws BancoDeDadosException {
        return usuarioRepository.listarTodos().stream()
                .map(usuario -> objectMapper.convertValue(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    public List<UsuarioDTO> listarPorStatus(int status) throws BancoDeDadosException {
        return listarTodos().stream().filter(usuarioDTO -> usuarioDTO.getStatus().ordinal() == status).collect(Collectors.toList());
    }

    public UsuarioDTO listarPorId(Integer id) throws Exception {
        return objectMapper.convertValue(usuarioRepository.listarPorId(id), UsuarioDTO.class);
    }

    public UsuarioDTO atualizar(int id, UsuarioCreateDTO usuarioDTO) throws Exception {
        if (usuarioDTO.getCNPJ().isBlank() && usuarioDTO.getCPF().isBlank()) {
            throw new RegraDeNegocioException("Documentação vazia");
        }
        Usuario usuario = objectMapper.convertValue(usuarioDTO, Usuario.class);
        UsuarioDTO usuarioDTO2 = objectMapper.convertValue(usuarioRepository.atualizar(id, usuario), UsuarioDTO.class);
        System.out.printf("Usuário com o ID %d atualizado.\n", id);
        emailService.sendEmail(usuario, 2);
        return usuarioDTO2;
    }

    public UsuarioDTO listarPorEmail(String email) throws BancoDeDadosException {
            return objectMapper.convertValue(usuarioRepository.listarPorEmail(email), UsuarioDTO.class );
    }

    public List<UsuarioDTO> rankearUsuarios() throws BancoDeDadosException {
        return usuarioRepository.rankearJogadores().stream().map(usuario ->
                objectMapper.convertValue(usuario, UsuarioDTO.class)).collect(Collectors.toList());
    }

    public UsuarioDTO delete(int id) throws Exception {

        Usuario usuarioProcurado = usuarioRepository.listarPorId(id);
        usuarioProcurado.setStatus(Status.Inativo);
        usuarioRepository.atualizar(id, usuarioProcurado);
        return objectMapper.convertValue(usuarioProcurado, UsuarioDTO.class);
    }
}


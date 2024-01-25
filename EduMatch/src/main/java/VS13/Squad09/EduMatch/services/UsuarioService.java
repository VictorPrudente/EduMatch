package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.request.UsuarioCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
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


    public UsuarioDTO salvar(UsuarioCreateDTO usuarioDTO) throws Exception {

        log.info("Criando usuario");
        Usuario usuarioEntity = objectMapper.convertValue(usuarioDTO, Usuario.class);
        usuarioRepository.adicionar(usuarioEntity);
        System.out.println("\nUsuário cadastrado com sucesso!");
        UsuarioDTO usuarioDTO2 = objectMapper.convertValue(usuarioEntity, UsuarioDTO.class);
        emailService.sendEmail(usuarioEntity, 1);
        return usuarioDTO2;
    }

    public List<UsuarioDTO> listarTodos() throws BancoDeDadosException {
        return usuarioRepository.listar().stream()
                .map(usuario -> objectMapper.convertValue(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());

    }

    public UsuarioDTO listarPorId(Integer id) throws Exception {
        return objectMapper.convertValue(usuarioRepository.listarPorId(id), UsuarioDTO.class);
    }

    public List<UsuarioDTO> rankearUsuarios() throws BancoDeDadosException {
        return usuarioRepository.rankearJogadores().stream().map(usuario ->
                objectMapper.convertValue(usuario, UsuarioDTO.class)).collect(Collectors.toList());
    }

    public Usuario listarPorEmail(String email) {
        try {
            return usuarioRepository.listarPorEmail(email);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UsuarioDTO atualizar(int id, UsuarioCreateDTO usuario) throws Exception {
        Usuario usuarioEntity = objectMapper.convertValue(usuario, Usuario.class);
        UsuarioDTO usuarioDTO = objectMapper.convertValue(usuarioRepository.editar(id, usuarioEntity), UsuarioDTO.class);
        System.out.printf("Usuário com o ID %d atualizado.\n", id);
        emailService.sendEmail(usuarioEntity, 2);
        return usuarioDTO;
    }

    public void delete(int id) throws Exception {
        Usuario usuarioProcurado = usuarioRepository.listarPorId(id);
        usuarioRepository.deletar(usuarioProcurado);
        log.info("Usuário Removido!");
        emailService.sendEmail(usuarioProcurado, 3);

    }
}


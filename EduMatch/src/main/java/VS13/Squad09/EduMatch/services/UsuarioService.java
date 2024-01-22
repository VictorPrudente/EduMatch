package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.request.UsuarioCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.entities.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.BancoDeDadosException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import repository.UsuarioRepository;

import java.util.*;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Slf4j
public class UsuarioService implements Service<Usuario> {

    private final ObjectMapper objectMapper;
    private final UsuarioRepository usuarioRepository;


    @Override
    public UsuarioDTO salvar(UsuarioCreateDTO usuarioDTO) throws BancoDeDadosException {

        log.info("Criando usuario");

        try {
            Usuario usuarioEntity = objectMapper.convertValue(usuarioDTO, Usuario.class);
            usuarioRepository.adicionar(usuarioEntity);
            System.out.println("\nUsuário cadastrado com sucesso!");
            UsuarioDTO usuarioDTO2 = objectMapper.convertValue(usuarioEntity, UsuarioDTO.class);
            return usuarioDTO2;
        } catch (BancoDeDadosException e) {
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<UsuarioDTO> listarTodos () {
        try {
            return usuarioRepository.listar().stream()
                    .map(usuario -> objectMapper.convertValue(usuario, UsuarioDTO.class))
                    .collect(Collectors.toList());
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UsuarioDTO listarPorId ( Integer id) throws Exception {
        try {
            return objectMapper.convertValue(usuarioRepository.listarPorId(id), UsuarioDTO.class);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

        public List<Usuario> rankearUsuarios () {
            try {
                return usuarioRepository.rankearJogadores();
            } catch (BancoDeDadosException e) {
                e.printStackTrace();
            }
            System.out.println("Algo deu errado.");
            return null;
        }

        public Usuario listarPorEmail (String email){
            try {
                return usuarioRepository.listarPorEmail(email);
            } catch (BancoDeDadosException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public UsuarioDTO atualizar ( int id, UsuarioCreateDTO usuario){
            try {
                Usuario usuarioEntity = objectMapper.convertValue(usuario, Usuario.class);
                return objectMapper.convertValue(usuarioRepository.editar(id, usuarioEntity), UsuarioDTO.class);
                System.out.printf("Usuário com o ID %d atualizado.\n", id);
            } catch (BancoDeDadosException e) {
                e.printStackTrace();
            }
            System.out.println("Usuário não atualizado.\n");
            return null;
        }
        @Override
        public void delete (int id){
            try {
                Usuario usuarioProcurado = usuarioRepository.listarPorId(id);
                usuarioRepository.deletar(usuarioProcurado);
                log.info("Usuário Removido!");
            } catch (BancoDeDadosException e) {
                e.printStackTrace();
            }
            log.info("Usuário não removido.");
        }
    }


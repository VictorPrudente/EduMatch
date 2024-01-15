package utils;

import entities.Usuario;
import exceptions.BancoDeDadosException;
import repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;

public class Login {

    UsuarioRepository usuarioRepository = new UsuarioRepository();

    public Optional<Usuario> Loguin(String cpf) throws BancoDeDadosException {
        List<Usuario> usuarios = usuarioRepository.listar();
        return usuarios.stream().filter(usuario -> usuario.getCPF().equals(cpf)).findFirst();
    }
}
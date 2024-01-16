package services;

import entities.Usuario;
import exceptions.BancoDeDadosException;
import interfaces.Service;
import repository.UsuarioRepository;

import java.util.*;

public class UsuarioService implements Service<Usuario> {

    private UsuarioRepository usuarioRepository;

    public UsuarioService() {
        usuarioRepository = new UsuarioRepository();
    }

    @Override
    public boolean salvar(Usuario usuario){
        try {
            if(usuario.getCPF().length() != 11){
                throw new Exception("CPF Inválido.");
            }
            Usuario user = usuarioRepository.adicionar(usuario);
            System.out.println("\nUsuário cadastrado com sucesso!");
            return true;
        } catch (BancoDeDadosException e){
            System.out.println("ERRO: " + e.getMessage());
        } catch (Exception e){
            System.out.println("ERRO: " + e.getMessage());
        }
        System.out.println("Usuário não cadastrado. Tente novamente");
        return false;
    }

    @Override
    public void listarTodos(){
        try {
            List<Usuario> usuarios = usuarioRepository.listar();
            usuarios.forEach(System.out::println);
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }

    public List<Usuario> rankearUsuarios(){
        try {
            return usuarioRepository.rankearJogadores();
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
        System.out.println("Algo deu errado.");
        return null;
    }

    public Usuario listarPorId(int id) throws Exception {
        try {
            Usuario usuario = usuarioRepository.listarPorId(id);
            return usuario;
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean atualizar(int id,Usuario usuario){
        try {
            usuarioRepository.editar(id, usuario);
            System.out.printf("Usuário com o ID %d atualizado.\n", id);
            return true;
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
        System.out.println("Usuário não atualizado.\n");
        return false;
    }
    @Override
    public boolean deletar(int id){
        try {
            usuarioRepository.remover(id);
            System.out.printf("Usuário com o id %d removido com sucesso.", id);
            return true;
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
        System.out.println("Usuário não removido.");
        return false;
    }
}

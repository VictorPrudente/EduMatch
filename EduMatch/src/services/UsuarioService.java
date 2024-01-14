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
            System.out.println("Usuário cadastrado com sucesso: " + user);
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

    public void rankearUsuarios(){
        try {
            List<Usuario> usuarios = usuarioRepository.rankearJogadores();
            int i = 0;
            for (Usuario usuario : usuarios) {
                System.out.printf("""
                                      
                        -=-=-=-=-=-=-=-=-=-
                        %d° Lugar
                        %s""", ++i, usuario.toString());
            }
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
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
            System.out.printf("Usuário com o ID %d atualizado.", id);
            return true;
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
        System.out.println("Usuário não atualizado.");
        return false;
    }
    @Override
    public boolean deletar(Usuario usuario){
        int id = usuario.getId();
        try {
            usuarioRepository.remover(id);
            System.out.printf("Usuário com o id %d removido com sucesso.");
            return true;
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
        System.out.println("Usuário não removido.");
        return false;
    }
}

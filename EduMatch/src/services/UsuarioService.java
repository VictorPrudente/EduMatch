package services;

import entities.Endereco;
import entities.Usuario;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class UsuarioService {

    private AtomicInteger COUNTER = new AtomicInteger();
    Integer id = COUNTER.incrementAndGet();
    Random random = new Random();
    private static ArrayList<Usuario> usuarios = new ArrayList<>();


    public UsuarioService() {
        inicializarLista();
    }

    private void inicializarLista(){
        usuarios.add(new Usuario(id, "Pedro", "Brown", "123123", random.nextInt(15, 50)));
        usuarios.add(new Usuario(id, "Lucio", "Blue", "123", random.nextInt(15, 50)));
        usuarios.add(new Usuario(id, "Marcos", "Yellow", "123", random.nextInt(15, 50)));
        usuarios.add(new Usuario(id, "Lucia", "White", "123", random.nextInt(15, 50)));
        usuarios.add(new Usuario(id, "Maria", "Black", "123", random.nextInt(15, 50)));
        usuarios.add(new Usuario(id, "Yolanda", "Red", "123", random.nextInt(15, 50)));
        usuarios.add(new Usuario(id, "Josefina", "Purple", "123", random.nextInt(15, 50)));
        usuarios.add(new Usuario(id, "Carlas", "Gold", "123", random.nextInt(15, 50)));
        usuarios.add(new Usuario(id, "Leslie", "Pink", "123", random.nextInt(15, 50)));

    }
    public boolean salvar(Usuario usuario){
        for (Usuario usuarioNaLista : usuarios){
            if (usuarioNaLista.getCPF().equals(usuario.getCPF())){
                System.out.println("O CPF deve ser único.");
                return false;
            }
        }
        usuarios.add(usuario);
        System.out.println("Usuário salvo com sucesso");
        return true;
    }

    public String listarTodos(){
        return usuarios.toString();
    }

    public String rankearUsuarios(){
        List<Usuario> rankingDeJogadores = usuarios
                .stream()
                .filter(usuario -> usuario.getPontuacao() != null)
                .sorted(Comparator.comparing(Usuario::getPontuacao).reversed())
                .collect(Collectors.toCollection(ArrayList::new));
        return rankingDeJogadores.toString();
    }

    public Usuario listarPorId(int id) throws Exception {
            for (Usuario usuario : usuarios) {
                if (usuario.getId() == id) {
                    return usuario;
                }
            }
            throw new Exception("Usuário com o ID " + id + " não encontrado.");
    }
    public Usuario listarPorCPF(String CPF) throws Exception {
        for (Usuario usuario : usuarios) {
            if (usuario.getCPF().equals(CPF)) {
                return usuario;
            }
        }
        throw new Exception("Usuário com o CPF " + CPF + " não encontrado.");
    }
    public void atualizar(int id, Usuario usuarioAtualizado){
        Usuario usuario = usuarios.get(id);
        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setSobrenome(usuarioAtualizado.getSobrenome());
        usuario.setIdade(usuarioAtualizado.getIdade());
        usuario.setEnderecos(usuarioAtualizado.getEnderecos());
        usuario.setContatos(usuarioAtualizado.getContatos());
    }

    public void deletar(int id, Usuario usuario){
        for (Usuario usuarioADeletar : usuarios){
            if (usuarioADeletar.getId() == id){
                usuarios.remove(usuarioADeletar);
            }
        }
    }
}

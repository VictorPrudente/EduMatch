package services;

import entities.Usuario;
import interfaces.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class UsuarioService implements Service<Usuario> {

    private AtomicInteger COUNTER = new AtomicInteger();
    Random random = new Random();
    private static ArrayList<Usuario> usuarios = new ArrayList<>();


    public UsuarioService() {
        inicializarLista();
    }

    private void inicializarLista(){
        Usuario usuario = new Usuario("Pedro", "Brown", "123123", random.nextInt(15, 50), random.nextInt(30));
        usuario.setId(COUNTER.incrementAndGet());
        usuarios.add(usuario);

        usuario = new Usuario("Lucio", "Blue", "123", random.nextInt(15, 50), random.nextInt(30));
        usuario.setId(COUNTER.incrementAndGet());
        usuarios.add(usuario);

        usuario = new Usuario("Marcos", "Yellow", "123", random.nextInt(15, 50), random.nextInt(30));
        usuario.setId(COUNTER.incrementAndGet());
        usuarios.add(usuario);
        usuario = new Usuario("Lucia", "White", "123", random.nextInt(15, 50), random.nextInt(30));
        usuario.setId(COUNTER.incrementAndGet());
        usuarios.add(usuario);
        usuario = new Usuario("Maria", "Black", "123", random.nextInt(15, 50), random.nextInt(30));
        usuario.setId(COUNTER.incrementAndGet());
        usuarios.add(usuario);
        usuario = new Usuario("Yolanda", "Red", "123", random.nextInt(15, 50), random.nextInt(30));
        usuario.setId(COUNTER.incrementAndGet());
        usuarios.add(usuario);
        usuario = new Usuario("Josefina", "Purple", "123", random.nextInt(15, 50), random.nextInt(30));
        usuario.setId(COUNTER.incrementAndGet());
        usuarios.add(usuario);
        usuario = new Usuario("Carlas", "Gold", "123", random.nextInt(15, 50), random.nextInt(30));
        usuario.setId(COUNTER.incrementAndGet());
        usuarios.add(usuario);
        usuario = new Usuario("Leslie", "Pink", "123", random.nextInt(15, 50), random.nextInt(30));
        usuario.setId(COUNTER.incrementAndGet());
        usuarios.add(usuario);
    }
    @Override
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

    @Override
    public void listarTodos(){
        for (Usuario usuario : usuarios){
            System.out.println(usuario.toString());
        }
    }

    public void rankearUsuarios(){
        List<Usuario> rankingDeJogadores = usuarios
                .stream()
                .filter(usuario -> usuario.getPontuacao() != null)
                .sorted(Comparator.comparing(Usuario::getPontuacao).reversed())
                .collect(Collectors.toCollection(ArrayList::new));
        for (Usuario jogadores : rankingDeJogadores){
            System.out.println(jogadores.toString());
        }
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
  
    @Override
    public boolean atualizar(int id,Usuario usuarioAtualizado){
        for (Usuario usuarioAtualizar : usuarios){
            if (usuarioAtualizar.getId() == id){
                usuarioAtualizar.setCPF(usuarioAtualizado.getCPF());
                usuarioAtualizar.setIdade(usuarioAtualizado.getIdade());
                usuarioAtualizar.setNome(usuarioAtualizado.getNome());
                return true;

            }
        }
        return false;

    }
    @Override
    public boolean deletar(Usuario usuario){
        return usuarios.remove(usuario);
    }
}

package services;

import entities.Endereco;
import exceptions.BancoDeDadosException;
import interfaces.Repositorio;
import interfaces.Service;
import repository.EnderecoRepository;
import java.util.List;

public class EnderecoService implements Service<Endereco> {

    private EnderecoRepository enderecoRepository;

    public EnderecoService() {
        enderecoRepository = new EnderecoRepository();
    }

    @Override
    public boolean salvar(Endereco endereco) {
        try {
            Endereco enderecoAdicionado = enderecoRepository.adicionar(endereco);
            System.out.println("Endereco cadastrado com sucesso! \n" + enderecoAdicionado);
            return true;
        } catch (BancoDeDadosException e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Endereco não cadastrado. Tente novamente");
        return false;
    }

    @Override
    public boolean deletar(int id) {
        try {
            enderecoRepository.remover(id);
            System.out.printf("Endereco com o id %d removido com sucesso.", id);
            return true;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        System.out.println("Endereco não removido.");
        return false;
    }

    @Override
    public boolean atualizar(int id, Endereco endereco) {
        try {
            enderecoRepository.editar(id, endereco);
            System.out.printf("Endereco com o ID %d atualizado.", id);
            return true;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        System.out.println("Endereco não atualizado.");
        return false;
    }

    @Override
    public void listarTodos() {
        try {
            List<Endereco> listar = enderecoRepository.listar();
            listar.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public Endereco listarPorDono(int id) {
        //este id vem da entidade que estiver chamando o seu endereco. Por exemplo, usuario.getId();
        try {
            return enderecoRepository.listarPorDono(id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        System.out.println("Endereco não encontrado.");
        return null;
    }

}
package services;

import entities.Contato;
import interfaces.Service;
import repository.ContatoRepository;
import exceptions.BancoDeDadosException;

import java.util.List;

public class ContatoService implements Service<Contato> {
    private ContatoRepository contatoRepository;

    public ContatoService() {
        contatoRepository = new ContatoRepository();
    }

    @Override
    public boolean salvar(Contato contato) {
        try {
            Contato contatoAdicionado = contatoRepository.adicionar(contato);
            System.out.println("Contato adicinado com sucesso! " + contatoAdicionado);
            return true;
        } catch (BancoDeDadosException e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Contato não cadastrado. Tente novamente");
        return false;
    }

    @Override
    public boolean deletar(int id) {
        try {
            contatoRepository.remover(id);
            System.out.printf("Contato com o id %d removido com sucesso.", id);
            return true;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        System.out.println("Contato não removido.");
        return false;
    }

    @Override
    public boolean atualizar(int id, Contato contato) {
        try {
            contatoRepository.editar(id, contato);
            System.out.printf("Contato com o ID %d atualizado.", id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        System.out.println("Contato não atualizado.");
        return false;
    }

    @Override
    public void listarTodos() {
        try {
            List<Contato> listar = contatoRepository.listar();
            listar.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public Contato listarPorDono(int id) {
        //este id vem da entidade que estiver chamando o seu contato. Por exemplo, usuario.getId();
        try {
            return contatoRepository.listarPorDono(id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        System.out.println("Contato não encontrado.");
        return null;
    }
}

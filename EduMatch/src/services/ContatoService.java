package services;

import entities.Contato;
import repository.ContatoRepository;
import exceptions.BancoDeDadosException;

import java.util.List;

public class ContatoService {
    private ContatoRepository contatoRepository;

    public ContatoService() {
        contatoRepository = new ContatoRepository();
    }

    // criação de um objeto
    public void adicionar(Contato contato) {
        try {
            Contato contatoAdicionado = contatoRepository.adicionar(contato);
            System.out.println("Contato adicinado com sucesso! " + contatoAdicionado);
        } catch (BancoDeDadosException e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // remoção
    public void remover(Integer id) {
        try {
            boolean conseguiuRemover = contatoRepository.remover(id);
            System.out.println("Contato removido? " + conseguiuRemover + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // atualização de um objeto
    public void editar(Integer id, Contato contato) {
        try {
            boolean conseguiuEditar = contatoRepository.editar(id, contato);
            System.out.println("Contato editado? " + conseguiuEditar + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // leitura
    public void listar() {
        try {
            List<Contato> listar = contatoRepository.listar();
            listar.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}

package services;

import entities.Portugues;
import exceptions.BancoDeDadosException;
import interfaces.Service;
import repository.PortuguesRepository;

public class PortuguesService implements Service<Portugues> {

    private PortuguesRepository portuguesRepository;


    public PortuguesService() {
        portuguesRepository = new PortuguesRepository();
    }


    @Override
    public boolean salvar(Portugues questao) {
        try {
            if (questao.getQuestao().isBlank()) {
                throw new Exception("Questão vazia.");
            }
            Portugues novaQuestao = portuguesRepository.adicionar(questao);
            System.out.println("Questão adicionada com sucesso! " + novaQuestao);
            return true;
        } catch (BancoDeDadosException e) {
            System.out.println("ERRO: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean atualizar(int id, Portugues portugues) {
        try {
            portuguesRepository.editar(id, portugues);
            System.out.println("Questão com ID " + id + " editada.");
            return true;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletar(int id) {
        try {
            portuguesRepository.remover(id);
            System.out.println("Questão com ID " + id + " removida");
            return true;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void listarTodos() {

    }


    public Portugues listarPelaDificuldade(int i) {
        try {
            return portuguesRepository.listarPorDificuldade(i);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }
}



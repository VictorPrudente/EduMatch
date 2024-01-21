package services;

import entities.Matematica;
import entities.enums.Dificuldades;
import exceptions.BancoDeDadosException;
import interfaces.Service;
import repository.MatematicaRepository;
import java.util.ArrayList;
import java.util.List;

public class MatematicaService implements Service<Matematica> {

    private MatematicaRepository matematicaRepository;


    public MatematicaService(){
        matematicaRepository = new MatematicaRepository();
    }


    @Override
    public boolean salvar(Matematica questao) {
        try {
            if(questao.getQuestao().isBlank()){
                throw new Exception("Questão vazia.");
            }
            Matematica novaQuestao = matematicaRepository.adicionar(questao);
            System.out.println("Questão adicionada com sucesso! " + novaQuestao);
            return true;
        } catch (BancoDeDadosException e){
            System.out.println("ERRO: " + e.getMessage());
        } catch (Exception e){
            System.out.println("ERRO: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean atualizar(int id, Matematica matematica) {
        try {
            matematicaRepository.editar(id, matematica);
            System.out.println("Questão com ID " + id + " editada.");
            return true;
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletar(int id) {
        try {
            matematicaRepository.remover(id);
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


    public Matematica listarPelaDificuldade(int i){
        try {
            return matematicaRepository.listarPorDificuldade(i);
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
        return null;
    }
}



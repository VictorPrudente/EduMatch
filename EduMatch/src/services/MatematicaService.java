package services;

import entities.Matematica;
import entities.enums.Dificuldades;
import exceptions.BancoDeDadosException;
import repository.MatematicaRepository;
import java.util.ArrayList;
import java.util.List;

public class MatematicaService{

    private MatematicaRepository matematicaRepository;


    public MatematicaService(){
        matematicaRepository = new MatematicaRepository();
    }


    public void salvar(Matematica questao) {
        try {
            if(questao.getQuestao().isBlank()){
                throw new Exception("Questão vazia.");
            }

            Matematica novaQuestao = matematicaRepository.adicionar(questao);
            System.out.println("Questão adicionada com sucesso! " + novaQuestao);
        } catch (BancoDeDadosException e){
            System.out.println("ERRO: " + e.getMessage());
        } catch (Exception e){
            System.out.println("ERRO: " + e.getMessage());
        }
    }


    public Matematica listarPelaDificuldade(int i){
        try {
            return matematicaRepository.listarPorDificuldade(i);
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
        return null;
    }


    public void atualizar(Integer id, Matematica pergunta){
        try {
            boolean editou = matematicaRepository.editar(id, pergunta);
            System.out.println("Questão com ID " + id + " editada? " + editou);
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }

    public void deletar(Integer id){
        try {
            boolean removeu = matematicaRepository.remover(id);
            System.out.println("Questão com ID " + id + " removida? " + removeu);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}


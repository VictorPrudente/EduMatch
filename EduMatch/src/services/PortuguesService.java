package services;

import entities.Portugues;
import entities.enums.Dificuldades;
import exceptions.BancoDeDadosException;
import repository.PortuguesRepository;
import java.util.ArrayList;
import java.util.List;

public class PortuguesService{

    private PortuguesRepository portuguesRepository;


    public PortuguesService(){
        portuguesRepository = new PortuguesRepository();
    }


    public void salvar(Portugues questao) {
        try {
            if(questao.getQuestao().isBlank()){
                throw new Exception("Questão vazia.");
            }

            Portugues novaQuestao = portuguesRepository.adicionar(questao);
            System.out.println("Questão adicionada com sucesso! " + novaQuestao);
        } catch (BancoDeDadosException e){
            System.out.println("ERRO: " + e.getMessage());
        } catch (Exception e){
            System.out.println("ERRO: " + e.getMessage());
        }
    }


    public List<Portugues> listarPelaDificuldade(int i){
        try {
            return portuguesRepository.listarPorDificuldade(i);
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
        return null;
    }


    public void atualizar(Integer id, Portugues pergunta){
        try {
            boolean editou = portuguesRepository.editar(id, pergunta);
            System.out.println("Questão com ID " + id + " editada? " + editou);
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }

    public void deletar(Integer id){
        try {
            boolean removeu = portuguesRepository.remover(id);
            System.out.println("Questão com ID " + id + " removida? " + removeu);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}


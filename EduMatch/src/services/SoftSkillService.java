package services;

import entities.SoftSkill;
import entities.enums.Dificuldades;
import exceptions.BancoDeDadosException;
import repository.SoftSkillRepository;

import java.util.ArrayList;
import java.util.List;

public class SoftSkillService{

    private SoftSkillRepository softSkillRepository;


    public SoftSkillService(){
        softSkillRepository = new SoftSkillRepository();
    }


    public void salvar(SoftSkill questao) {
        try {
            if(questao.getQuestao().isBlank()){
                throw new Exception("Questão vazia.");
            }

            SoftSkill novaQuestao = softSkillRepository.adicionar(questao);
            System.out.println("Questão adicionada com sucesso! " + novaQuestao);
        } catch (BancoDeDadosException e){
            System.out.println("ERRO: " + e.getMessage());
        } catch (Exception e){
            System.out.println("ERRO: " + e.getMessage());
        }
    }


    public SoftSkill listarPelaDificuldade(int i){
        SoftSkill questao = new SoftSkill();
        try {
            return softSkillRepository.listarPorDificuldade(i);
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
        return null;
    }


    public void atualizar(Integer id, SoftSkill pergunta){
        try {
            boolean editou = softSkillRepository.editar(id, pergunta);
            System.out.println("Questão com ID " + id + " editada? " + editou);
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }

    public void deletar(Integer id){
        try {
            boolean removeu = softSkillRepository.remover(id);
            System.out.println("Questão com ID " + id + " removida? " + removeu);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}


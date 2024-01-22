package services;

import entities.SoftSkill;
import exceptions.BancoDeDadosException;
import interfaces.Service;
import repository.SoftSkillRepository;

public class SoftSkillService implements Service<SoftSkill> {

    private SoftSkillRepository softSkillRepository;


    public SoftSkillService() {
        softSkillRepository = new SoftSkillRepository();
    }


    @Override
    public boolean salvar(SoftSkill questao) {
        try {
            if (questao.getQuestao().isBlank()) {
                throw new Exception("Questão vazia.");
            }
            SoftSkill novaQuestao = softSkillRepository.adicionar(questao);
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
    public boolean atualizar(int id, SoftSkill softSkill) {
        try {
            softSkillRepository.editar(id, softSkill);
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
            softSkillRepository.remover(id);
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


    public SoftSkill listarPelaDificuldade(int i) {
        try {
            return softSkillRepository.listarPorDificuldade(i);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }
}



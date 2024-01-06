package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SoftSkillsService {
    private ArrayList<Usuario> usuarios;
    Random random = new Random();
    private SoftSkills softSkills = new SoftSkills();

    private void inicializarLista(){

    }
    public void salvar (String questao, int dificuldade){
        if (dificuldade == 1){
            softSkills.getPerguntasFaceis().add(questao);
        } else if (dificuldade == 2) {
            softSkills.getPerguntasMedias().add(questao);
        } else if (dificuldade == 3) {
            softSkills.getPerguntasDificeis().add(questao);
        }
    }
    public boolean atualizar(int dificuldade, int id, String questao){
       if (dificuldade == 1 && id < softSkills.getPerguntasFaceis().size()){
           softSkills.getPerguntasFaceis().set(id, questao);
       } else if (dificuldade == 2 && id < softSkills.getPerguntasMedias().size()) {
           softSkills.getPerguntasMedias().set(id, questao);
       } else if (dificuldade == 3 && id < softSkills.getPerguntasDificeis().size()) {
           softSkills.getPerguntasDificeis().set(id, questao);
       } else {
           System.err.print("Não foi possível atualizar a questão, favor verifique os dados e tente novamente");
           return false;
       }
        return true;
    }
    public boolean deletar (int dificuldade, int id){
        if (dificuldade == 1 && id < softSkills.getPerguntasFaceis().size()){
            softSkills.getPerguntasFaceis().remove(id);
        } else if (dificuldade == 2 && id < softSkills.getPerguntasMedias().size()) {
            softSkills.getPerguntasMedias().remove(id);
        } else if (dificuldade == 3 && id < softSkills.getPerguntasMedias().size()) {
            softSkills.getPerguntasDificeis().remove(id);
        } else {
            System.err.print("Não foi possível deletar a questão, favor verifique os dados e tente novamente");
            return false;
        }
        return true;
    }
    public void listarTodos(){
        System.out.println("Perguntas Fáceis: ");
        softSkills.getPerguntasFaceis().stream().forEach(pergunta -> System.out.println(pergunta));
        System.out.println("Perguntas Médias: ");
        softSkills.getPerguntasMedias().stream().forEach(pergunta -> System.out.println(pergunta));
        System.out.println("Perguntas Difíceis: ");
        softSkills.getPerguntasDificeis().stream().forEach(pergunta -> System.out.println(pergunta));


    }
}

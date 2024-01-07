package services;

import entities.Matematica;
import entities.Usuario;
import entities.enums.Dificuldades;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class MatematicaService {

    private AtomicInteger COUNTER = new AtomicInteger();

    private List<Matematica> listaQuestesMatematica = new ArrayList<>();

    public MatematicaService() {
        inicializarListas();
    }

    private void inicializarListas() {
        listaQuestesMatematica.add(new Matematica(COUNTER.incrementAndGet(), Dificuldades.FACIL, """
                
                Para a excursão da escola, a escola alugou um ônibus que tem 29 lugares.
                Somente 18 adolescentes confirmaram que irão na viagem. 
                Quantos lugasres irão sobrar?
                A) 10
                B) 9
                C) 11
                D) 12
                E) 8""", "C", 1));
        listaQuestesMatematica.add(new Matematica(COUNTER.incrementAndGet(), Dificuldades.FACIL, """
                
                Ana esta de aniversário, ela convidou 17 meninos e 27 meninas.
                Quantos convidados vai ter no total da festa?
                A) 40
                B) 34
                C) 42
                D) 37
                E) 44""", "E", 1));
        listaQuestesMatematica.add(new Matematica(COUNTER.incrementAndGet(), Dificuldades.FACIL, """
                
                Joao precisa de 15 cadernos. 
                Seu pai lhe deu 7 e sua mãe deu 6 cadernos. 
                Faltou quantos cadernos para o João?
                A) 1
                B) 2
                C) 4
                D) 3
                E) 5""", "B", 1));

        listaQuestesMatematica.add(new Matematica(COUNTER.incrementAndGet(), Dificuldades.MEDIO, """
                
                Qual é o dobro de 27,5?
                A) 61
                B) 60
                C) 51
                D) 54
                E) 55""", "E", 3));
        listaQuestesMatematica.add(new Matematica(COUNTER.incrementAndGet(), Dificuldades.MEDIO, """
                
                Quanto é 963 divido por 3?
                A) 321
                B) 431
                C) 323
                D) 421
                E) 325""", "A", 3));
        listaQuestesMatematica.add(new Matematica(COUNTER.incrementAndGet(), Dificuldades.MEDIO, """
                
                Qual é o triplo de 562?
                A) 1684
                B) 1686
                C) 1124
                D) 1844
                E) 1680""", "B", 3));
        listaQuestesMatematica.add(new Matematica(COUNTER.incrementAndGet(), Dificuldades.DIFICIL, """
                
                Qual é o antecessor do número 81, multiplicado por 2?
                A) 160
                B) 162
                C) 180
                D) 145
                E) 161""", "A", 5));

        listaQuestesMatematica.add(new Matematica(COUNTER.incrementAndGet(), Dificuldades.DIFICIL, """
                
                Qual é o sucessor do número 101, dividido por 3?
                A) 34
                B) 52
                C) 36
                D) 50
                E) 32""", "A", 5));
        listaQuestesMatematica.add(new Matematica(COUNTER.incrementAndGet(), Dificuldades.DIFICIL, """
                
                Qual a soma dos lados de 6 triangulos?
                A) 18
                B) 9
                C) 16
                D) 6
                E) 3""", "A", 5));
    }

    public boolean salvar(Matematica novaQuestao) {
        for (Matematica questaoExistente : listaQuestesMatematica) {
            if (questaoExistente.getQuestao().equals(novaQuestao.getQuestao())) {
                System.out.println("Questão já cadastrada");
                return false;
            }
        }
        System.out.println("Questão cadastrada com sucesso.");
        return true;
    }

    public void listarTodos(){
        for (Matematica questao : listaQuestesMatematica){
            System.out.println(questao.toString());
        }
    }

    //TODO implementar retorno de mensagem caso não encontre uma questão;
    public List<Matematica> listarPelaDificuldade(Dificuldades dificuldade){
        ArrayList<Matematica> questoes = new ArrayList<>();
        for (Matematica questao : listaQuestesMatematica){
            if (questao.getDificuldade() == dificuldade){
               questoes.add(questao);
            }
        }
        return questoes;
    }

    public void atualizar(int id, String textoAtualizado, String opcaoCorreta){
        Optional<Matematica> questao = listaQuestesMatematica.stream().filter(questao1 -> questao1.getId() == id).findFirst();

        questao.ifPresent(questao1 -> {
            questao1.setQuestao(textoAtualizado);
            questao1.setOpcaoCerta(opcaoCorreta);});
        System.out.println("Questão atualizada com sucesso!");
    }

    public void deletar(int id){
        for (Matematica questaoADeletar : listaQuestesMatematica){
            if (questaoADeletar.getId() == id){
                listaQuestesMatematica.remove(questaoADeletar);
            }
        }
    }
}

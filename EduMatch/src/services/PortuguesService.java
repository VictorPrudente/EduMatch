package services;

import entities.Usuario;
import entities.enums.Dificuldades;
import entities.Portugues;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class PortuguesService {

    private AtomicInteger COUNTER = new AtomicInteger();

    private List<Portugues> listaQuestesPortugues = new ArrayList<>();

    public PortuguesService() {
        inicializarListas();
    }

    private void inicializarListas() {
        listaQuestesPortugues.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.FACIL, """
                Qual é o sinônimo da palavra "rápido"?
                a) Devagar
                b) Lento
                c) Ágil 
                d) Manso
                e) Estático""", "C", 1));
        listaQuestesPortugues.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.FACIL, """
                Qual é o antônimo da palavra "alegre"?
                a) Triste 
                b) Feliz
                c) Radiante
                d) Contente
                e) Jubiloso""", "A", 1));
        listaQuestesPortugues.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.FACIL, """
                Qual é o sinônimo da palavra "grande"?
                a) Pequeno
                b) Gigante 
                c) Largo
                d) Longo
                e) Curto""", "B", 1));

        listaQuestesPortugues.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.MEDIO, """
                Qual é o plural correto de "cidadão"?
                a) Cidadãos 
                b) Cidadãoes
                c) Cidadõeis
                d) Cidadões
                e) Cidadães""", "A", 3));
        listaQuestesPortugues.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.MEDIO, """
                Qual é o significado da expressão "a gota d'água"?
                a) Algo pequeno e irrelevante 
                b) Uma grande conquista
                c) Uma chuva intensa
                d) Uma expressão de alegria
                e) Uma demonstração de coragem""", "A", 3));
        listaQuestesPortugues.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.MEDIO, """
                Qual é o autor da obra "Dom Casmurro"?
                a) Machado de Assis 
                b) José de Alencar
                c) Lima Barreto
                d) Graciliano Ramos
                e) Monteiro Lobato
                """, "A", 3));
        listaQuestesPortugues.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.DIFICIL, """
                Na frase "Aquele gesto revelou uma benevolência incomum", o que significa "benevolência"?
                a) Maldade
                b) Crueldade
                c) Bondade 
                d) Arrogância
                e) Desprezo
                """, "C", 5));

        listaQuestesPortugues.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.DIFICIL, """
                Na frase "A tessitura da trama era intricada", o que significa "tessitura"?
                a) Textura 
                b) Espessura
                c) Altura
                d) Largura
                e) Densidade""", "A", 5));
        listaQuestesPortugues.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.DIFICIL, """
                Qual é o significado da expressão "chover no molhado"?
                a) Discutir algo desnecessário 
                b) Lidar com um problema difícil
                c) Estar sempre ocupado
                d) Conseguir algo com facilidade
                e) Chorar por algo perdido""", "A", 5));
    }

    public boolean salvar(Portugues novaQuestao) {
        for (Portugues questaoExistente : listaQuestesPortugues) {
            if (questaoExistente.getQuestao().equals(novaQuestao.getQuestao())) {
                System.out.println("Questão já cadastrada");
                return false;
            }
        }
        System.out.println("Questão cadastrada com sucesso.");
        return true;
    }

    public void listarTodos(){
        for (Portugues questao : listaQuestesPortugues){
            System.out.println(questao.toString());
        }
    }

    //TODO implementar retorno de mensagem caso não encontre uma questão;
    public void listarPelaDificuldade(Dificuldades dificuldade){
        for (Portugues questao : listaQuestesPortugues){
            if (questao.getDificuldade() == dificuldade){
                System.out.println(questao.toString());
            }
        }
    }

    public void atualizar(int id, String textoAtualizado, String opcaoCorreta){
        Optional<Portugues> questao = listaQuestesPortugues.stream().filter(questao1 -> questao1.getId() == id).findFirst();

        questao.ifPresent(questao1 -> {
            questao1.setQuestao(textoAtualizado);
            questao1.setOpcaoCerta(opcaoCorreta);});
        System.out.println("Questão atualizada com sucesso!");
    }

    public void deletar(int id){
        for (Portugues questaoADeletar : listaQuestesPortugues){
            if (questaoADeletar.getId() == id){
                listaQuestesPortugues.remove(questaoADeletar);
            }
        }
    }
}
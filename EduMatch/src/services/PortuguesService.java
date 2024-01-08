package services;

import entities.Contato;
import entities.Usuario;
import entities.enums.Dificuldades;
import entities.Portugues;
import interfaces.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class PortuguesService implements Service<Portugues> {
    private AtomicInteger COUNTER = new AtomicInteger();

    private List<Portugues> perguntas = new ArrayList<>();

    public PortuguesService() {
        inicializarListas();
    }

    private void inicializarListas() {
        perguntas.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.FACIL, """
                
                Qual é o sinônimo da palavra "rápido"?
                
                A) Devagar
                B) Lento
                C) Ágil 
                D) Manso
                E) Estático""", "C", 1));
        perguntas.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.FACIL, """
                
                Qual é o antônimo da palavra "alegre"?
                
                A) Triste 
                B) Feliz
                C) Radiante
                D) Contente
                E) Jubiloso""", "A", 1));
        perguntas.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.FACIL, """
                
                Qual é o sinônimo da palavra "grande"?
                
                A) Pequeno
                B) Gigante 
                C) Largo
                D) Longo
                E) Curto""", "B", 1));

        perguntas.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.MEDIO, """
                
                Qual é o plural correto de "cidadão"?
                
                A) Cidadãos 
                B) Cidadãoes
                C) Cidadõeis
                D) Cidadões
                E) Cidadães""", "A", 3));
        perguntas.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.MEDIO, """
                
                Qual é o significado da expressão "a gota d'água"?
                
                A) Algo pequeno e irrelevante 
                B) Uma grande conquista
                C) Uma chuva intensa
                D) Uma expressão de alegria
                E) Uma demonstração de coragem""", "A", 3));
        perguntas.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.MEDIO, """
                
                Qual é o autor da obra "Dom Casmurro"?
                
                A) Machado de Assis 
                B) José de Alencar
                C) Lima Barreto
                D) Graciliano Ramos
                E) Monteiro Lobato
                """, "A", 3));
        perguntas.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.DIFICIL, """
                
                Na frase "Aquele gesto revelou uma benevolência incomum", o que significa "benevolência"?
                
                A) Maldade
                B) Crueldade
                C) Bondade 
                D) Arrogância
                E) Desprezo
                """, "C", 5));

        perguntas.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.DIFICIL, """
                Na frase "A tessitura da trama era intricada", o que significa "tessitura"?
                A) Textura 
                B) Espessura
                C) Altura
                D) Largura
                E) Densidade""", "A", 5));
        perguntas.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.DIFICIL, """
                Qual é o significado da expressão "chover no molhado"?
                A) Discutir algo desnecessário 
                B) Lidar com um problema difícil
                C) Estar sempre ocupado
                D) Conseguir algo com facilidade
                E) Chorar por algo perdido""", "A", 5));
    }
    @Override
    public boolean salvar(Portugues novaQuestao) {
        for (Portugues questaoExistente : perguntas) {
            if (questaoExistente.getQuestao().equals(novaQuestao.getQuestao())) {
                System.out.println("Questão já cadastrada");
                return false;
            }
        }
        System.out.println("Questão cadastrada com sucesso.");
        return true;
    }

    @Override
    public void listarTodos(){
        for (Portugues questao : perguntas){
            System.out.println(questao.toString());
        }
    }

    //TODO implementar retorno de mensagem caso não encontre uma questão;

    public List<Portugues> listarPelaDificuldade(Dificuldades dificuldade){
        ArrayList<Portugues> questoes = new ArrayList<>();
        for (Portugues questao : perguntas){

            if (questao.getDificuldade() == dificuldade){
                questoes.add(questao);
            }
        }
        return questoes;
    }
    @Override
    public boolean atualizar(int id, Portugues pergunta){
        for (Portugues perguntaAtualizar : perguntas) {
            if (perguntaAtualizar.getId() == id) {
                perguntaAtualizar.setDificuldade(pergunta.getDificuldade());
                perguntaAtualizar.setOpcaoCerta(pergunta.getOpcaoCerta());
                perguntaAtualizar.setQuestao(pergunta.getQuestao());
                perguntaAtualizar.setPontos(pergunta.getPontos());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletar(Portugues pergunta){
        return perguntas.remove(pergunta);
    }
}
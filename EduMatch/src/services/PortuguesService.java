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
                a) Devagar
                b) Lento
                c) Ágil 
                d) Manso
                e) Estático""", "C", 1));
        perguntas.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.FACIL, """
                Qual é o antônimo da palavra "alegre"?
                a) Triste 
                b) Feliz
                c) Radiante
                d) Contente
                e) Jubiloso""", "A", 1));
        perguntas.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.FACIL, """
                Qual é o sinônimo da palavra "grande"?
                a) Pequeno
                b) Gigante 
                c) Largo
                d) Longo
                e) Curto""", "B", 1));

        perguntas.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.MEDIO, """
                Qual é o plural correto de "cidadão"?
                a) Cidadãos 
                b) Cidadãoes
                c) Cidadõeis
                d) Cidadões
                e) Cidadães""", "A", 3));
        perguntas.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.MEDIO, """
                Qual é o significado da expressão "a gota d'água"?
                a) Algo pequeno e irrelevante 
                b) Uma grande conquista
                c) Uma chuva intensa
                d) Uma expressão de alegria
                e) Uma demonstração de coragem""", "A", 3));
        perguntas.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.MEDIO, """
                Qual é o autor da obra "Dom Casmurro"?
                a) Machado de Assis 
                b) José de Alencar
                c) Lima Barreto
                d) Graciliano Ramos
                e) Monteiro Lobato
                """, "A", 3));
        perguntas.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.DIFICIL, """
                Na frase "Aquele gesto revelou uma benevolência incomum", o que significa "benevolência"?
                a) Maldade
                b) Crueldade
                c) Bondade 
                d) Arrogância
                e) Desprezo
                """, "C", 5));

        perguntas.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.DIFICIL, """
                Na frase "A tessitura da trama era intricada", o que significa "tessitura"?
                a) Textura 
                b) Espessura
                c) Altura
                d) Largura
                e) Densidade""", "A", 5));
        perguntas.add(new Portugues(COUNTER.incrementAndGet(), Dificuldades.DIFICIL, """
                Qual é o significado da expressão "chover no molhado"?
                a) Discutir algo desnecessário 
                b) Lidar com um problema difícil
                c) Estar sempre ocupado
                d) Conseguir algo com facilidade
                e) Chorar por algo perdido""", "A", 5));
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
    public void listarPelaDificuldade(Dificuldades dificuldade){
        for (Portugues questao : perguntas){
            if (questao.getDificuldade() == dificuldade){
                System.out.println(questao.toString());
            }
        }
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
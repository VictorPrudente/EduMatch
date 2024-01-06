package services;

import entities.SoftSkill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class SoftSkillService {
    private AtomicInteger COUNTER = new AtomicInteger();


    private ArrayList<SoftSkill> perguntasFaceis = new ArrayList<>();
    private ArrayList <SoftSkill> perguntasMedias = new ArrayList<>();
    private ArrayList <SoftSkill> perguntasDificeis = new ArrayList<>();


    public SoftSkillService (){
        inicializarListaFacil();
        inicializarListaMedia();
        inicializarListaDificil();
    }
    private void inicializarListaFacil(){
        perguntasFaceis.add(new SoftSkill(COUNTER.incrementAndGet(),"""
            Qual habilidades é fundamental para um bom trabalho em equipe?
            A) Falar alto
            B) Chegar no horário
            C) Ter mais conhecimento que o líder
            D) Falar várias línguas
            E) Empatia""", "E", 1));
        perguntasFaceis.add(new SoftSkill(COUNTER.incrementAndGet(), """
            Como ser proativo no trabalho?
            A) Fazer somente quando é solicitado
            B) Tomar ações preventivas e antecipar problemas
            C) Fazer o trabalho do colega
            D) Não se propor a aprender novas funções
            E) Realizar apenas tarefas fáceis""", "B", 1));
        perguntasFaceis.add(new SoftSkill(COUNTER.incrementAndGet(), """
            Qual habilidade é fundamental para uma boa comunicação?
            A) Falar alto para ser ouvido por todos
            B) Escutar ativamente
            C) Utilizar vocabulário técnico
            D) Falar o tempo todo para garantir que todos saibam que você está presente
            E) Evitar contato visual para se concentrar nas palavras""", "B", 1));
    }
    private void inicializarListaMedia(){
        perguntasMedias.add(new SoftSkill(COUNTER.incrementAndGet(), """
            Como a inteligência emocional agrega no ambiente profissional?
            A) Fazer o trabalho rápido
            B) Não tem necessidade de falar com os colegas
            C) Ajuda em épocas de crise e no trabalho em equipe
            D) Não precisar pedir ajuda, quando estiver com dúvida
            E) Não leva em consideração o sentimento dos colegas""", "C",3));
        perguntasMedias.add(new SoftSkill(COUNTER.incrementAndGet(), """
            O que é resiliência no ambiente profissional?
            A) Não enfrentar os desafios
            B) Habilidade de se adaptar a mudanças e superar dificuldades
            C) Evitar qualquer tipo de risco
            D) Saber dizer “não”
            E) Fazer qualquer coisa que é solicitado""", "B",3));
        perguntasMedias.add(new SoftSkill(COUNTER.incrementAndGet(), """
            O que é empatia e como ela melhora o ambiente de trabalho?
            A) Dar presentes aos colegas
            B) Capacidade de compreender e se colocar no lugar do outro
            C) Expressar apenas emoções positivas para manter um ambiente agradável
            D) Falar somente o que o colega quer ouvir
            E) Somente dar feedback positivo""", "B",3));
    }
    private void inicializarListaDificil(){
        perguntasDificeis.add(new SoftSkill(COUNTER.incrementAndGet(), """
            Por que a gestão do tempo é essencial no ambiente profissional?
            A)  Para todos os colegas saberem quando deve ser feito a entrega
            B) Gestão do tempo é essencial, somente em equipes grandes
            C) Maximizar a produtividade e a eficiência
            D) Não precisar controlar os prazos
            E) Priorizar somente grandes projetos""", "C", 5));
        perguntasDificeis.add(new SoftSkill(COUNTER.incrementAndGet(),  """
            Qual é a importância da inclusão no local de trabalho?
            A)  Reduzir a inovação
            B) Reduzir a criatividade
            C) Ampliar perspectivas e promover um ambiente mais inclusivo
            D) Aumentar a competição entre os funcionários
            E) Evitar diferentes opiniões""", "C", 5));
        perguntasDificeis.add(new SoftSkill(COUNTER.incrementAndGet(), """
            Como a resolução de problemas desafiadores contribui para o crescimento profissional?\s
            A) Reduzindo a capacidade analítica
            B) Diminuindo o aprendizado
            C) Desenvolvendo habilidades de resolução de problemas
            D) Diminuindo a adaptabilidade
            E) Diminuindo a empatia""", "C", 5));
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

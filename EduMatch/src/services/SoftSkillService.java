package services;

import entities.SoftSkill;
import entities.Usuario;
import entities.enums.Dificuldades;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class SoftSkillService {

    private AtomicInteger COUNTER = new AtomicInteger();

    private List<SoftSkill> listaQuestesSoftSkill = new ArrayList<>();

    public SoftSkillService() {
        inicializarListas();
    }

    private void inicializarListas() {
        listaQuestesSoftSkill.add(new SoftSkill(COUNTER.incrementAndGet(), Dificuldades.FACIL, """
                Qual habilidades é fundamental para um bom trabalho em equipe?
                A) Falar alto
                B) Chegar no horário
                C) Ter mais conhecimento que o líder
                D) Falar várias línguas
                E) Empatia""", "E", 1));
        listaQuestesSoftSkill.add(new SoftSkill(COUNTER.incrementAndGet(), Dificuldades.FACIL, """
                Como ser proativo no trabalho?
                A) Fazer somente quando é solicitado
                B) Tomar ações preventivas e antecipar problemas
                C) Fazer o trabalho do colega
                D) Não se propor a aprender novas funções
                E) Realizar apenas tarefas fáceis""", "B", 1));
        listaQuestesSoftSkill.add(new SoftSkill(COUNTER.incrementAndGet(), Dificuldades.FACIL, """
                Qual habilidade é fundamental para uma boa comunicação?
                A) Falar alto para ser ouvido por todos
                B) Escutar ativamente
                C) Utilizar vocabulário técnico
                D) Falar o tempo todo para garantir que todos saibam que você está presente
                E) Evitar contato visual para se concentrar nas palavras""", "B", 1));

        listaQuestesSoftSkill.add(new SoftSkill(COUNTER.incrementAndGet(), Dificuldades.MEDIO, """
                Como a inteligência emocional agrega no ambiente profissional?
                A) Fazer o trabalho rápido
                B) Não tem necessidade de falar com os colegas
                C) Ajuda em épocas de crise e no trabalho em equipe
                D) Não precisar pedir ajuda, quando estiver com dúvida
                E) Não leva em consideração o sentimento dos colegas""", "A", 3));
        listaQuestesSoftSkill.add(new SoftSkill(COUNTER.incrementAndGet(), Dificuldades.MEDIO, """
                O que é resiliência no ambiente profissional?
                A) Não enfrentar os desafios
                B) Habilidade de se adaptar a mudanças e superar dificuldades
                C) Evitar qualquer tipo de risco
                D) Saber dizer “não”
                E) Fazer qualquer coisa que é solicitado""", "A", 3));
        listaQuestesSoftSkill.add(new SoftSkill(COUNTER.incrementAndGet(), Dificuldades.MEDIO, """
                O que é empatia e como ela melhora o ambiente de trabalho?
                A) Dar presentes aos colegas
                B) Capacidade de compreender e se colocar no lugar do outro
                C) Expressar apenas emoções positivas para manter um ambiente agradável
                D) Falar somente o que o colega quer ouvir
                E) Somente dar feedback positivo
                """, "A", 3));
        listaQuestesSoftSkill.add(new SoftSkill(COUNTER.incrementAndGet(), Dificuldades.DIFICIL, """
                Por que a gestão do tempo é essencial no ambiente profissional?
                A)  Para todos os colegas saberem quando deve ser feito a entrega
                B) Gestão do tempo é essencial, somente em equipes grandes
                C) Maximizar a produtividade e a eficiência
                D) Não precisar controlar os prazos
                E) Priorizar somente grandes projetos""", "A", 5));

        listaQuestesSoftSkill.add(new SoftSkill(COUNTER.incrementAndGet(), Dificuldades.DIFICIL, """
                Qual é a importância da inclusão no local de trabalho?
                A)  Reduzir a inovação
                B) Reduzir a criatividade
                C) Ampliar perspectivas e promover um ambiente mais inclusivo
                D) Aumentar a competição entre os funcionários
                E) Evitar diferentes opiniões""", "A", 5));
        listaQuestesSoftSkill.add(new SoftSkill(COUNTER.incrementAndGet(), Dificuldades.DIFICIL, """
                Como a resolução de problemas desafiadores contribui para o crescimento profissional?\s
                A) Reduzindo a capacidade analítica
                B) Diminuindo o aprendizado
                C) Desenvolvendo habilidades de resolução de problemas
                D) Diminuindo a adaptabilidade
                E) Diminuindo a empatia""", "A", 5));
    }

    public boolean salvar(SoftSkill novaQuestao) {
        for (SoftSkill questaoExistente : listaQuestesSoftSkill) {
            if (questaoExistente.getQuestao().equals(novaQuestao.getQuestao())) {
                System.out.println("Questão já cadastrada");
                return false;
            }
        }
        System.out.println("Questão cadastrada com sucesso.");
            return true;
    }

    public void listarTodos(){
        for (SoftSkill questao : listaQuestesSoftSkill){
            System.out.println(questao.toString());
        }
    }

    //TODO implementar retorno de mensagem caso não encontre uma questão;
    public void listarPelaDificuldade(Dificuldades dificuldade){
        for (SoftSkill questao : listaQuestesSoftSkill){
            if (questao.getDificuldade() == dificuldade){
                System.out.println(questao.toString());
            }
            }
        }

    public void atualizar(int id, String textoAtualizado, String opcaoCorreta){
        Optional<SoftSkill> questao = listaQuestesSoftSkill.stream().filter(questao1 -> questao1.getId() == id).findFirst();

        questao.ifPresent(questao1 -> {
            questao1.setQuestao(textoAtualizado);
            questao1.setOpcaoCerta(opcaoCorreta);});
        System.out.println("Questão atualizada com sucesso!");
    }

    public void deletar(int id){
        for (SoftSkill questaoADeletar : listaQuestesSoftSkill){
            if (questaoADeletar.getId() == id){
                listaQuestesSoftSkill.remove(questaoADeletar);
            }
        }
    }
}

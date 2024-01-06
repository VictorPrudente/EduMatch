package entities;

import java.util.ArrayList;
import java.util.Arrays;

public class SoftSkills extends Game{
    private ArrayList<String> perguntasFaceis = new ArrayList<>(Arrays.asList("""
            Qual habilidades é fundamental para um bom trabalho em equipe?
            A) Falar alto
            B) Chegar no horário
            C) Ter mais conhecimento que o líder
            D) Falar várias línguas
            E) Empatia""", """
            Como ser proativo no trabalho?
            A) Fazer somente quando é solicitado
            B) Tomar ações preventivas e antecipar problemas
            C) Fazer o trabalho do colega
            D) Não se propor a aprender novas funções
            E) Realizar apenas tarefas fáceis""", """
            Qual habilidade é fundamental para uma boa comunicação?
            A) Falar alto para ser ouvido por todos
            B) Escutar ativamente
            C) Utilizar vocabulário técnico
            D) Falar o tempo todo para garantir que todos saibam que você está presente
            E) Evitar contato visual para se concentrar nas palavras"""));

    private ArrayList <String> perguntasMedias = new ArrayList<>(Arrays.asList("""
            Como a inteligência emocional agrega no ambiente profissional?
            A) Fazer o trabalho rápido
            B) Não tem necessidade de falar com os colegas
            C) Ajuda em épocas de crise e no trabalho em equipe
            D) Não precisar pedir ajuda, quando estiver com dúvida
            E) Não leva em consideração o sentimento dos colegas""","oi", """
            O que é resiliência no ambiente profissional?
            A) Não enfrentar os desafios
            B) Habilidade de se adaptar a mudanças e superar dificuldades
            C) Evitar qualquer tipo de risco
            D) Saber dizer “não”
            E) Fazer qualquer coisa que é solicitado""", """
            O que é empatia e como ela melhora o ambiente de trabalho?
            A) Dar presentes aos colegas
            B) Capacidade de compreender e se colocar no lugar do outro
            C) Expressar apenas emoções positivas para manter um ambiente agradável
            D) Falar somente o que o colega quer ouvir
            E) Somente dar feedback positivo
            """));
    private ArrayList <String> perguntasDificeis = new ArrayList<>(Arrays.asList("""
            Por que a gestão do tempo é essencial no ambiente profissional?
            A)  Para todos os colegas saberem quando deve ser feito a entrega
            B) Gestão do tempo é essencial, somente em equipes grandes
            C) Maximizar a produtividade e a eficiência
            D) Não precisar controlar os prazos
            E) Priorizar somente grandes projetos""", """
            Qual é a importância da inclusão no local de trabalho?
            A)  Reduzir a inovação
            B) Reduzir a criatividade
            C) Ampliar perspectivas e promover um ambiente mais inclusivo
            D) Aumentar a competição entre os funcionários
            E) Evitar diferentes opiniões""", """
            Como a resolução de problemas desafiadores contribui para o crescimento profissional?\s
            A) Reduzindo a capacidade analítica
            B) Diminuindo o aprendizado
            C) Desenvolvendo habilidades de resolução de problemas
            D) Diminuindo a adaptabilidade
            E) Diminuindo a empatia"""));

    public ArrayList<String> getPerguntasFaceis() {
        return perguntasFaceis;
    }

    public void setPerguntasFaceis(ArrayList<String> perguntasFaceis) {
        this.perguntasFaceis = perguntasFaceis;
    }

    public ArrayList<String> getPerguntasMedias() {
        return perguntasMedias;
    }

    public void setPerguntasMedias(ArrayList<String> perguntasMedias) {
        this.perguntasMedias = perguntasMedias;
    }

    public ArrayList<String> getPerguntasDificeis() {
        return perguntasDificeis;
    }

    public void setPerguntasDificeis(ArrayList<String> perguntasDificeis) {
        this.perguntasDificeis = perguntasDificeis;
    }
    //        public Certificado emitirCertificado(Usuario usuario){
//
//    }
}

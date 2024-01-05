package entities;

public class Game {
    private int pontos;
    private String modalidade;
    private int dificuldade;
    private int vitorias;
    private int derrotas;

    public Game(){}
    public Game(String modalidade, int dificuldade) {
        this.modalidade = modalidade;
        this.dificuldade = dificuldade;
    }

    public void escolherModalidade(String modalidade){
        this.modalidade= modalidade;
    }
    public void escolherDificuldade(int dificuldade){
        this.dificuldade = dificuldade;
    }

    public void comecarJogo (){
        System.out.println("O jogo começou!");
    }

    public void ganharJogo(){
        System.out.println("Parabéns! Você venceu!");
        pontos += 15 * dificuldade;
        vitorias ++;
    }

    public void desistir (){
        System.out.println("Você desistiu dessa partida!");
        derrotas ++;
        pontos -= 5;
    }

    public void perderJogo(){
        System.out.println("Poxa, infelizmente você perdeu! ");
        derrotas++;
        pontos -=5;
    }
    public void semErros(){
        System.out.println("Parabéns, você não errou nada!");
        pontos += 10;
    }

    public int getPontos() {
        return pontos;
    }

    public String getModalidade() {
        return modalidade;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public int getVitorias() {
        return vitorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }
}

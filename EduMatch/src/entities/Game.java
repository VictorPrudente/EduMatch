package entities;

public class Game {
    public int pontos;
    public String modalidade;
    public int dificuldade;
    public int vitorias;
    public int derrotas;

    public void escolherModalidade(String modalidade){
        this.modalidade= modalidade;
    }
    public void escolherDificuldade(int diff){
        this.dificuldade = diff;
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


}

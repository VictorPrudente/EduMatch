package entities;

import java.util.Objects;

public abstract class Game{

    protected int id;
    protected String questao;
    protected String opcaoCerta;
    protected int pontos;
    public Game(){

    }

    public Game(int id, String questao, String opcaoCerta, int pontos) {
        this.id = id;
        this.questao = questao;
        this.opcaoCerta = opcaoCerta;
        this.pontos = pontos;
    }

    public String getQuestao() {
        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    public String getOpcaoCerta() {
        return opcaoCerta;
    }

    public void setOpcaoCerta(String opcaoCerta) {
        this.opcaoCerta = opcaoCerta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
}

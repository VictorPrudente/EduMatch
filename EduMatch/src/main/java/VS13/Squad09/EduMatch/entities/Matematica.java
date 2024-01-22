package VS13.Squad9.EduMatch.entities;

import entities.enums.Dificuldades;

public class Matematica extends entities.Game {


    public Matematica() {
    }

    public Matematica(int id, Dificuldades dificuldades, String questao, String opcaoCerta, int pontos) {
        super(id, dificuldades, questao, opcaoCerta, pontos);
    }

    @Override
    public String toString() {
        return getQuestao();
    }
}

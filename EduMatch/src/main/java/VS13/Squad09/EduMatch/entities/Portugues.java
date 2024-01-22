package VS13.Squad9.EduMatch.entities;

import VS13.Squad9.EduMatch.entities.enums.Dificuldades;

public class Portugues extends Game {

    public Portugues() {
    }

    public Portugues(int id, Dificuldades dificuldades, String questao, String opcaoCerta, int pontos) {
        super(id, dificuldades, questao, opcaoCerta, pontos);
    }

    @Override
    public String toString() {
        return getQuestao();
    }
}

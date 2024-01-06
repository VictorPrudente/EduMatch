package entities;

import entities.enums.Dificuldades;

public class SoftSkill extends Game{

    public SoftSkill() {
    }

    public SoftSkill(int id, Dificuldades dificuldades, String questao, String opcaoCerta, int pontos) {
        super(id, dificuldades, questao, opcaoCerta, pontos);
    }

    @Override
    public String toString() {
        return getQuestao();
    }
}
package entities.enums;

public enum Games {

    PORTUGUES(0),
    MATEMATICA(1),
    SOFT_SKILLS(2);

    private int tipo;

    Games(int tipo){
    }


    public int getTipo(){
        return tipo;
    }

    public static Games trilhas(int tipo){
        for (Games trilha : Games.values()){
            if (trilha.ordinal() == tipo){
                return trilha;
            }
        }
        throw new IllegalStateException("Tipo de Contato não encontrado");
    }
}

package entities.enums;

public enum Dificuldades {


    FACIL(0),
    MEDIO(1),
    DIFICIL(2);


    private int nivel;

    public int getNivel() {
        return nivel;
    }
    public static Dificuldades valueOf(int nivel){
        for (Dificuldades dificuldade : Dificuldades.values()){
            if (dificuldade.ordinal() == nivel){
                return dificuldade;
            }
        }
        throw new IllegalStateException("Nível de dificuldade não encontrado.");
    }

    Dificuldades(int i) {
    }
}

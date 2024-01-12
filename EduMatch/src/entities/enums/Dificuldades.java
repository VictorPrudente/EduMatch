package entities.enums;

public enum Dificuldades {


    FACIL(0, "FACIL"),
    MEDIO(1, "MEDIO"),
    DIFICIL(2, "DIFICIL");


    private int nivel;
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

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

    public static String getDificuldade(int nivel){
        for (Dificuldades dificuldade : Dificuldades.values()){
            if (dificuldade.ordinal() == nivel){
                return dificuldade.getDescricao();
            }
        }
        throw new IllegalStateException("Nível de dificuldade não encontrado.");
    }

    Dificuldades(int nivel, String descricao) {
    }
}

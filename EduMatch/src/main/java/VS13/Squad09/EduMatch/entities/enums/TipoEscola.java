package VS13.Squad9.EduMatch.entities.enums;

public enum TipoEscola {
    PRIVADA(0),
    PUBLICA(1),
    TECNICA(2);

    private int tipo;

    TipoEscola(int tipo){
    }


    public int getTipo(){
        return tipo;
    }

    public static TipoEscola valueOf(int tipo){
        for (TipoEscola tipoEscola : TipoEscola.values()){
            if (tipoEscola.ordinal() == tipo){
                return tipoEscola;
            }
        }
        throw new IllegalStateException("Tipo de Escola não encontrado");
    }
}
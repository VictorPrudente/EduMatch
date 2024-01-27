package VS13.Squad09.EduMatch.entities.enums;

import java.util.Arrays;

public enum TipoDocumento {
    CPF(0),
    CNPJ(1);

    private int tipo;

    TipoDocumento(int tipo){
    }


    public Integer getTipo(){
        return tipo;
    }

    public static TipoDocumento valueOf(Integer tipo){
        return Arrays.stream(TipoDocumento.values())
                .filter(tipoDocumento -> tipoDocumento.getTipo().equals(tipo))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Tipo de Documento não encontrado"));
    }
}

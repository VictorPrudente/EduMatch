package VS13.Squad09.EduMatch.entities.enums;

import java.util.Arrays;

public enum Role {
    ADM(0),
    PF(1),
    PJ(2);

    private int tipo;

    Role(int tipo){
    }


    public Integer getTipo(){
        return tipo;
    }

    public static Role valueOf(int tipo){
        return Arrays.stream(Role.values())
                .filter(tipoRole -> tipoRole.getTipo().equals(tipo))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Tipo de Documento não encontrado"));
    }
}

package VS13.Squad09.EduMatch.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Role {
    ADM(0),
    PF(1),
    PJ(2);

    private Integer tipo;

    public static Role valueOf(Integer tipo){
        return Arrays.stream(Role.values())
                .filter(tipoRole -> tipoRole.getTipo().equals(tipo))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Tipo de Role não encontrado"));
    }
}

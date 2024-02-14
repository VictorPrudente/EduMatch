package VS13.Squad09.EduMatch.entities.enums;

import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Role {
    ADM(0),
    PF(1),
    PJ(2);

    private final Integer tipo;

    public static Role valueOf(Integer tipo) throws NaoEncontradoException {
        return Arrays.stream(Role.values())
                .filter(tipoRole -> tipoRole.getTipo().equals(tipo))
                .findFirst()
                .orElseThrow(() -> new NaoEncontradoException("Tipo de Role não encontrado"));
    }
}

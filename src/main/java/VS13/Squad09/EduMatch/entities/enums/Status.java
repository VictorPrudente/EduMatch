package VS13.Squad09.EduMatch.entities.enums;

import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Status {

    INATIVO(0),
    ATIVO(1);


    private final Integer tipo;


    public static Status valueOf(Integer tipo) throws NaoEncontradoException {
        return Arrays.stream(Status.values())
                .filter(status -> status.getTipo().equals(tipo))
                .findFirst()
                .orElseThrow(() -> new NaoEncontradoException("Status não encontrado."));
    }

}

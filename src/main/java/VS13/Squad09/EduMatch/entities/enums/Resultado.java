package VS13.Squad09.EduMatch.entities.enums;

import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Resultado {

    REPROVADO(0),
    APROVADO(1);

    private final Integer resultado;

    public static Resultado valueOf(Integer resultado) throws NaoEncontradoException {
        return Arrays.stream(Resultado.values()).filter(r -> r.getResultado().equals(resultado))
                .findFirst()
                .orElseThrow(() -> new NaoEncontradoException("Nenhum resultado encontrado com este valor."));
    }

}

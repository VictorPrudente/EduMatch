package VS13.Squad09.EduMatch.entities.enums;

import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;

@Slf4j
@Getter
@AllArgsConstructor
public enum Dificuldade {

    FACIL(0),
    MEDIO(1),
    DIFICIL(2);

    private final Integer nivel;


    public static Dificuldade valueOf(Integer nivel) throws NaoEncontradoException {
        log.info("Retornando " + nivel);
        return Arrays.stream(Dificuldade.values())
                .filter(dificuldades -> dificuldades.getNivel().equals(nivel))
                .findFirst().orElseThrow(() -> new NaoEncontradoException("Nenhuma dificuldade encontrada com este nível."));
    }
}

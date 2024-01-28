package VS13.Squad09.EduMatch.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Getter
@AllArgsConstructor
@Slf4j
public enum Dificuldade {

    FACIL(1),
    MEDIO(2),
    DIFICIL(3);

    private Integer nivel;


    public static Dificuldade valueOf(Integer nivel){
        log.info("Retornando " + nivel);
        return Arrays.stream(Dificuldade.values())
                .filter(dificuldades -> dificuldades.getNivel().equals(nivel))
                .findFirst().get();
    }
}

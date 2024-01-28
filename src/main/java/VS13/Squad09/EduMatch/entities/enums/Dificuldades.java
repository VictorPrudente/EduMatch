package VS13.Squad09.EduMatch.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
@Slf4j
public enum Dificuldades {

    FACIL(1),
    MEDIO(2),
    DIFICIL(3);

    private Integer nivel;


    public static Dificuldades valueOf(Integer nivel){
        log.info("Retornando " + nivel);
        return Arrays.stream(Dificuldades.values())
                .filter(dificuldades -> dificuldades.getNivel().equals(nivel))
                .findFirst().get();
    }
}

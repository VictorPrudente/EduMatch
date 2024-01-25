package VS13.Squad09.EduMatch.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum Dificuldades {

    FACIL(1),
    MEDIO(2),
    DIFICIL(3);

    private Integer nivel;


    public static Dificuldades valueOf(int nivel){
        return Arrays.stream(Dificuldades.values())
                .filter(dificuldades -> dificuldades.getNivel().equals(nivel))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Nível de Dificuldade não encontrado"));
    }
}

package VS13.Squad09.EduMatch.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Trilha {

    PORTUGUES(0),
    MATEMATICA(1),
    SOFT_SKILLS(2);

    private Integer tipo;


    public static Trilha valueOf(Integer tipo){
        return Arrays.stream(Trilha.values())
                .filter(trilha -> trilha.getTipo().equals(tipo))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Trilha não encontrada."));
    }
}

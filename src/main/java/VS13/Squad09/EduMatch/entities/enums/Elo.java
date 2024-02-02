package VS13.Squad09.EduMatch.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Elo {

    FERRO(0),
    BRONZE(1),
    PRATA(2),
    OURO(3),
    RUBY(4),
    AMETISTA(5),
    DIAMANTE(6),
    MENTE_BRILHANTE(7),
    MENTE_RADIANTE(8),
    MASTER_MIND(9);


    private Integer elo;

    public static Elo valueOf(Integer ranking){
        return Arrays.stream(Elo.values())
                .filter(elo -> elo.getElo().equals(ranking))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Tipo de Role não encontrado"));
    }
}

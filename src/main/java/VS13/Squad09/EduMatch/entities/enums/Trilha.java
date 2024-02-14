package VS13.Squad09.EduMatch.entities.enums;

import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Trilha {

    PORTUGUES(0),
    MATEMATICA(1),
    SOFT_SKILLS(2),
    GEOGRAFIA(3),
    HISTORIA(4),
    FISICA(5),
    QUIMICA(6),
    BIOLOGIA(7),
    ATUALIDADES(8),
    ESPANHOL(9),
    INGLES(10);


    private final Integer tipo;


    public static Trilha valueOf(Integer tipo) throws NaoEncontradoException {
        return Arrays.stream(Trilha.values())
                .filter(trilha -> trilha.getTipo().equals(tipo))
                .findFirst()
                .orElseThrow(() -> new NaoEncontradoException("Trilha não encontrada."));
    }
}

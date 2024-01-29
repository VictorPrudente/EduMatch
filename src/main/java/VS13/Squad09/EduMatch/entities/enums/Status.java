package VS13.Squad09.EduMatch.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Status {

    INATIVO(0),
    ATIVO(1);


    private Integer tipo;


    public static Status valueOf(Integer tipo){
        return Arrays.stream(Status.values())
                .filter(status -> status.getTipo().equals(tipo))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Status não encontrado."));
    }

}

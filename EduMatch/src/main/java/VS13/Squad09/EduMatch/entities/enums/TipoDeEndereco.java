package VS13.Squad09.EduMatch.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TipoDeEndereco {
    RESIDENCIAL(0),
    COMERCIAL(1);

    private Integer tipo;


    public static TipoDeEndereco valueOf(int tipo) {
        return Arrays.stream(TipoDeEndereco.values())
                .filter(tipoDeEndereco -> tipoDeEndereco.getTipo().equals(tipo))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Tipo de Contato não encontrado"));
    }

}

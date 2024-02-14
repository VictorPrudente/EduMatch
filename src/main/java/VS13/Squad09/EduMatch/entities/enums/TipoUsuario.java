package VS13.Squad09.EduMatch.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;


@Getter
@AllArgsConstructor
public enum TipoUsuario {
    ADMINISTRADOR(0),
    PESSOA_JURIDICA(1),
    PESSOA_FISICA(2);

    private Integer tipo;

    public static TipoUsuario valueOf(Integer tipo){
        return Arrays.stream(TipoUsuario.values())
                .filter(tipoDocumento -> tipoDocumento.getTipo().equals(tipo))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Tipo de Documento não encontrado"));
    }
}

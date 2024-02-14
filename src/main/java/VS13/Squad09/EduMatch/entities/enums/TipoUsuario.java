package VS13.Squad09.EduMatch.entities.enums;

import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;


@Getter
@AllArgsConstructor
public enum TipoUsuario {
    ADMINISTRADOR(0),
    PESSOA_FISICA(1),
    PESSOA_JURIDICA(2);

    private final Integer tipo;


    public static TipoUsuario valueOf(Integer tipo) throws NaoEncontradoException {
        return Arrays.stream(TipoUsuario.values())
                .filter(tipoDocumento -> tipoDocumento.getTipo().equals(tipo))
                .findFirst()
                .orElseThrow(() -> new NaoEncontradoException("Tipo de permissão não encontrado"));
    }
}

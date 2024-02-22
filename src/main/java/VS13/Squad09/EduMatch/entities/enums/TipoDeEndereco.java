package VS13.Squad09.EduMatch.entities.enums;

import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Arrays;


@Getter
@AllArgsConstructor
public enum TipoDeEndereco {
        RESIDENCIAL(0),
        COMERCIAL(1);

        private final Integer tipo;


        public static TipoDeEndereco valueOf(int tipo) throws NaoEncontradoException {
            return Arrays.stream(TipoDeEndereco.values())
                    .filter(tipoDeEndereco -> tipoDeEndereco.getTipo().equals(tipo))
                    .findFirst()
                    .orElseThrow(() -> new NaoEncontradoException("Tipo de Contato não encontrado"));
        }
    }

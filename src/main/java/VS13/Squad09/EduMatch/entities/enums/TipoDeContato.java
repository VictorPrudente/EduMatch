package VS13.Squad09.EduMatch.entities.enums;


import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum TipoDeContato {
        CELULAR(0),
        RESIDENCIAL(1),
        COMERCIAL(2);

        private final Integer tipo;


        public static TipoDeContato valueOf(int tipo) throws NaoEncontradoException {
            for (TipoDeContato tipoContato : TipoDeContato.values()){
                if (tipoContato.ordinal() == tipo){
                    return tipoContato;
                }
            }
            throw new NaoEncontradoException("Tipo de Contato não encontrado");
        }
    }

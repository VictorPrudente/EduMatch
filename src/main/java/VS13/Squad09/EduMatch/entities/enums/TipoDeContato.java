package VS13.Squad09.EduMatch.entities.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum TipoDeContato {
        CELULAR(0),
        RESIDENCIAL(1),
        COMERCIAL(2);

        private Integer tipo;


        public static TipoDeContato valueOf(int tipo){
            for (TipoDeContato tipoContato : TipoDeContato.values()){
                if (tipoContato.ordinal() == tipo){
                    return tipoContato;
                }
            }
            throw new IllegalStateException("Tipo de Contato não encontrado");
        }
    }

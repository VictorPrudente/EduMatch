package VS13.Squad9.EduMatch.entities.enums;

import java.util.Arrays;

public enum TipoDeEndereco {
        RESIDENCIAL(0),
        COMERCIAL(1);

        private int tipo;

        TipoDeEndereco(int tipo){
        }


        public Integer getTipo(){
            return tipo;
        }

        public static TipoDeEndereco valueOf(int tipo){
            return Arrays.stream(TipoDeEndereco.values())
                    .filter(tipoDeEndereco -> tipoDeEndereco.getTipo().equals(tipo))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Tipo de Contato não encontrado"));
        }
    }

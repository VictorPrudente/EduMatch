package entities.enums;

public enum TipoDeContato {
        CELULAR(0),
        RESIDENCIAL(1),
        COMERCIAL(2);

        private int tipo;

        TipoDeContato(int tipo){
        }


        public int getTipo(){
            return tipo;
        }

        public static TipoDeContato valueOf(int tipo){
            for (TipoDeContato tipoContato : TipoDeContato.values()){
                if (tipoContato.ordinal() == tipo){
                    return tipoContato;
                }
            }
            throw new IllegalStateException("Tipo de Contato não encontrado");
        }
    }

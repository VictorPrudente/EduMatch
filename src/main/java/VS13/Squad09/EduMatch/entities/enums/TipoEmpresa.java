package VS13.Squad09.EduMatch.entities.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoEmpresa {
    PRIVADA(0),
    PUBLICA(1),
    TECNICA(2),
    USUARIO_PADRAO(3);

    private Integer tipo;


    public static TipoEmpresa valueOf(int tipo){
        for (TipoEmpresa tipoEmpresa : TipoEmpresa.values()){
            if (tipoEmpresa.ordinal() == tipo){
                return tipoEmpresa;
            }
        }
        throw new IllegalStateException("Tipo de Contato não encontrado");
    }
}
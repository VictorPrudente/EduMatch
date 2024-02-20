package VS13.Squad09.EduMatch.dtos.response;

import VS13.Squad09.EduMatch.entities.enums.TipoLog;
import VS13.Squad09.EduMatch.entities.enums.TipoOperacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogDTO {

    private String id;
    private TipoLog tipoLog;
    private TipoOperacao tipoOperacao;
    private String descricao;
    private String data;
}

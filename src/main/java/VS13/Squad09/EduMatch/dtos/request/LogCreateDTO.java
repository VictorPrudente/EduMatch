package VS13.Squad09.EduMatch.dtos.request;

import VS13.Squad09.EduMatch.entities.enums.TipoLog;
import VS13.Squad09.EduMatch.entities.enums.TipoOperacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogCreateDTO {
    @Enumerated(EnumType.STRING)
    private TipoLog tipoLog;
    private TipoOperacao tipoOperacao;
    private String descricao;
    private String data;
}

package VS13.Squad09.EduMatch.dtos.response;

import VS13.Squad09.EduMatch.entities.enums.TipoDeContato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContatoDTO {
    private String telefone;
    private String descricao;
    private TipoDeContato tipo;
}

package VS13.Squad09.EduMatch.entities;
import VS13.Squad09.EduMatch.entities.enums.TipoDeContato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contato {
    private Integer id;
    private String descricao;
    private String telefone;
    private TipoDeContato tipo;
    private Integer idUsuario;
}

package VS13.Squad9.EduMatch.entities;
import entities.enums.TipoDeContato;
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
    private Integer id_usuario;
}

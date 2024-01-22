package VS13.Squad09.EduMatch.dtos.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Integer id;
    private String nome;
    private String sobrenome;
    private Integer idade;
    private Integer pontuacao;
}

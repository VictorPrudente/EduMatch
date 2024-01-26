package VS13.Squad09.EduMatch.dtos.response;

import VS13.Squad09.EduMatch.entities.Certificado;
import VS13.Squad09.EduMatch.entities.Contato;
import VS13.Squad09.EduMatch.entities.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioDTO {
    private Integer id;
    private String nome;
    private String sobrenome;
    private String email;
    private String CPF;
    private Integer idade;
    private Integer pontuacao;
    private Integer idEscola;
    private Integer idEmpresa;
}

package VS13.Squad09.EduMatch.dtos.response;

import VS13.Squad09.EduMatch.entities.Certificado;
import VS13.Squad09.EduMatch.entities.Contato;
import VS13.Squad09.EduMatch.entities.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioDTO {
    private Integer id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String CPF;
    private Integer idade;
    private Integer pontuacao;
    private Endereco endereco;
    private Contato contato;
    private List<Certificado> certificados;
    private Integer id_escola;
    private Integer id_empresa;
}

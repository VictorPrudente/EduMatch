package VS13.Squad09.EduMatch.dtos.response;

import VS13.Squad09.EduMatch.entities.enums.TipoEmpresa;
import VS13.Squad09.EduMatch.entities.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaJuridicaDTO {
    private Integer id;
    private String email;
    private String nome;
    private String CNPJ;
    private TipoUsuario tipoUsuario;
    private TipoEmpresa tipoEmpresa;
}

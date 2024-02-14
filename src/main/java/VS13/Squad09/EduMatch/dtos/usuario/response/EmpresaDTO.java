package VS13.Squad09.EduMatch.dtos.usuario.response;
import VS13.Squad09.EduMatch.entities.enums.TipoUsuario;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDTO {
    private Integer id;
    private String email;
    private String nome;
    private String CNPJ;
}

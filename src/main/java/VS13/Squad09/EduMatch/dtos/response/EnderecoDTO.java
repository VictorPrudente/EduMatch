package VS13.Squad09.EduMatch.dtos.response;

import VS13.Squad09.EduMatch.dtos.request.EnderecoCreateDTO;
import VS13.Squad09.EduMatch.entities.enums.TipoDeEndereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {
    private Integer id;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private TipoDeEndereco tipoDeEndereco;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;
}

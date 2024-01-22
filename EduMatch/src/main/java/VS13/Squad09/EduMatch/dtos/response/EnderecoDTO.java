package VS13.Squad09.EduMatch.dtos.response;

import VS13.Squad09.EduMatch.entities.enums.TipoDeEndereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {
    private String logradouro;
    private Integer numero;
    private TipoDeEndereco tipo;
    private String complemento;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;
}

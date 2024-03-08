package VS13.Squad09.EduMatch.dtos.request;

import VS13.Squad09.EduMatch.entities.enums.TipoDeEndereco;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoCreateDTO {

    @Schema(description = "Nome da rua ou avenida", required = true, example = "Rua x")
    @NotBlank(message = "A rua não pode estar vazia.")
    private String logradouro;

    @Schema(description = "Número do endereço", required = true, example = "100")
    @NotNull(message = "O número não pode estar vazia.")
    private Integer numero;

    @Schema(description = "Tipo de endereço, sendo Residencial ou Comercial", required = true, example = "COMERCIAL")
    @NotNull(message = "O tipo de endereço não pode estar vazio.")
    private TipoDeEndereco tipoDeEndereco;

    @Schema(description = "Complemento do endereço", example = "Apto 200 | Esquina com Rua y")
    @Size(max = 50, message = "Complemento não deve conter mais que 50 caracteres.")
    private String complemento;

    @Schema(description = "CEP do endereço", required = true, example = "60123444")
    @Pattern(regexp = "^\\d{8}$", message = "CEP inválido.")
    private String cep;

    @Schema(description = "Cidade do endereço", required = true, example = "Fortaleza")
    @NotBlank(message = "O campo 'Cidade' não pode estar vazio.")
    private String cidade;

    @Schema(description = "Estado do endereço", required = true, example = "Ceará")
    @NotBlank(message = "O campo 'Estado' não pode estar vazio.")
    private String estado;

    @Schema(description = "País do endereço", required = true, example = "Brasil")
    @NotBlank(message = "O campo 'Pais' não pode estar vazio.")
    private String pais;
}


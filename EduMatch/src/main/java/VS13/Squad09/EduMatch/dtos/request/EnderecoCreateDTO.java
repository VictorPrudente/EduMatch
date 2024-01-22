package VS13.Squad09.EduMatch.dtos.request;

import VS13.Squad09.EduMatch.entities.enums.TipoDeEndereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoCreateDTO {

    private Integer id;

    private Integer id_usuario;

    @NotBlank(message = "A rua não pode estar vazia.")
    private String logradouro;

    @NotNull(message = "O número não pode estar vazia.")
    private Integer numero;

    @NotNull(message = "O tipo de endereço não pode estar vazio.")
    private TipoDeEndereco tipo;

    @Size(max = 50, message = "Complemento não deve conter mais que 50 caracteres.")
    private String complemento;

    @Pattern(regexp = "^\\d{8}$", message = "CEP inválido.")
    private String cep;

    @NotBlank(message = "O campo 'Cidade' não pode estar vazio.")
    private String cidade;

    @NotBlank(message = "O campo 'Estado' não pode estar vazio.")
    private String estado;

    @NotBlank(message = "O campo 'Pais' não pode estar vazio.")
    private String pais;

}


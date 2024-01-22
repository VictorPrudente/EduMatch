package VS13.Squad09.EduMatch.dtos.request;

import VS13.Squad09.EduMatch.entities.enums.TipoDeContato;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContatoCreateDTO {
    private Integer id;
    private Integer id_usuario;
    @Length(max = 50, message = "A descrição não pode ter mais q 50 caracteres.")
    private String descricao;
    @Size(min = 8, max = 9, message = "Telefone deve conter 9 digitos para celular ou 8 digitos para fixo.")
    private String telefone;
    @NotNull(message = "Tipo de contato não deve ser nulo.")
    private TipoDeContato tipo;
}

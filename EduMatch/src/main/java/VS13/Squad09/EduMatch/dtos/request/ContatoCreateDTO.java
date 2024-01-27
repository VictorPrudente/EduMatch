package VS13.Squad09.EduMatch.dtos.request;

import VS13.Squad09.EduMatch.entities.enums.TipoDeContato;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContatoCreateDTO {
    private Integer id;
    private Integer id_usuario;
    @Schema(description = "Informações adicionais sobre o contato", required = true, example = "Apenas ligações")
    @Length(max = 50, message = "A descrição não pode ter mais q 50 caracteres.")
    private String descricao;
    @Schema(description = "Numero do contato", required = true, example = "21998561236")
    @Size(min = 8, max = 9, message = "Telefone deve conter 9 digitos para celular ou 8 digitos para fixo.")
    private String telefone;
    @Schema(description = "Tipo do contato", required = true, example = "RESIDENCIAL")
    @NotNull(message = "Tipo de contato não deve ser nulo.")
    private TipoDeContato tipo;
}

package VS13.Squad09.EduMatch.dtos.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginCreateDTO {

    @NotBlank
    @Schema(description = "e-mail cadastrado para login", example = "fulano@email.com")
    private String email;

    @NotBlank
    @Schema(description = "Senha cadastrada para login", example = "MinhaSenha123*-*")
    private String senha;
}

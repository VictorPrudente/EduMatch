package VS13.Squad09.EduMatch.dtos.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCreateDTO {

        @NotBlank
        @Schema(description = "Nome do usuário", example = "fulano / ciclano LTDA", required = true)
        private String nome;

        @Schema(description = "Sobrenome do usuário", example = "Silva", required = true)
        private String sobrenome;

        @Email
        @Schema(description = "e-mail do usuário", example = "fulano@gmail.com", required = true)
        private String email;

        @NotBlank
        @Schema(description = "Senha do usuário", example = "OiTudoBem?123", required = true)
        private String senha;

        @Size(min = 11 , max = 11)
        @Schema(description = "CPF do usuário/PF", example = "12345678910")
        private String CPF;

        @Size(min = 14 , max = 14)
        @Schema(description = "CNPJ do usuário/PJ", example = "10123456000412")
        private String CNPJ;

        @Past
        @Schema(description = "Data de nascimento do usuário", example = "yyyy-mm-dd",required = true)
        private LocalDate dataNascimento;

        @Schema(description = "Foto de usuário")
        private String fotoUrl;
}


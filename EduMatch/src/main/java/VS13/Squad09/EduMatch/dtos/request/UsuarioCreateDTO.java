package VS13.Squad09.EduMatch.dtos.request;


import VS13.Squad09.EduMatch.entities.enums.Role;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.TipoUsuario;
import VS13.Squad09.EduMatch.entities.enums.TipoEmpresa;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCreateDTO {
        @Positive
        @Schema(description = "id do usuário", example = "1")
        private Integer id;

        @NotNull
        @NotBlank
        @Schema(description = "nome do usuário", example = "fulano / ciclano LTDA")
        private String nome;

        private Integer pontuacao;

        @Schema(description = "sobrenome do usuário", example = "Silva")
        private String sobrenome;

        @NotNull
        @NotBlank
        @Email
        @Schema(description = "e-mail do usuário", example = "fulano@gmail.com")
        private String email;

        @NotNull
        @NotBlank
        @Schema(description = "senha do usuário", example = "OiTudoBem?123")
        private String senha;

        @Size(min = 11 , max = 11)
        @Schema(description = "cpf do usuário/PF", example = "12345678910")
        private String CPF;

        @Size(min = 14 , max = 14)
        @Schema(description = "cnpj do usuário/PJ", example = "10123456000412")
        private String CNPJ;

        @NotNull
        @Schema(description = "Tipo do documento do usuário", example = "CPF")
        private TipoUsuario tipoUsuario;

        @NotNull
        @Schema(description = "permissão do usuário", example = "ADM")
        private Role role;

        @Past
        @Schema(description = "data de nascimento do usuário", example = "yyyy-mm-dd")
        private LocalDate dataNascimento;

        @NotNull
        @Schema(description = "Status do cadastro do usuário", example = "ATIVO")
        private Status status;

        @Schema(description = "Tipo de Empresa", example = "PRIVADA")
        private TipoEmpresa tipoEmpresa;

}

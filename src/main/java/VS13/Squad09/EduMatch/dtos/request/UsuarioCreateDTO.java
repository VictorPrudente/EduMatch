package VS13.Squad09.EduMatch.dtos.request;


import VS13.Squad09.EduMatch.entities.enums.Role;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.TipoUsuario;
import VS13.Squad09.EduMatch.entities.enums.TipoEmpresa;
import io.swagger.v3.oas.annotations.Hidden;
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
        @Hidden
        @Positive
        @Schema(description = "Id do usuário", example = "1")
        private Integer id;

        @NotNull
        @NotBlank
        @Schema(description = "Nome do usuário", example = "fulano / ciclano LTDA")
        private String nome;


        @Schema(description = "Sobrenome do usuário", example = "Silva")
        private String sobrenome;

        @NotNull
        @NotBlank
        @Email
        @Schema(description = "e-mail do usuário", example = "fulano@gmail.com")
        private String email;

        @NotNull
        @NotBlank
        @Schema(description = "Senha do usuário", example = "OiTudoBem?123")
        private String senha;

        @Size(min = 11 , max = 11)
        @Schema(description = "CPF do usuário/PF", example = "12345678910")
        private String CPF;

        @Size(min = 14 , max = 14)
        @Schema(description = "CNPJ do usuário/PJ", example = "10123456000412")
        private String CNPJ;

        @NotNull
        @Schema(description = "Tipo do documento do usuário", example = "CPF")
        private TipoUsuario tipoUsuario;

        @NotNull
        @Schema(description = "Permissão do usuário", example = "ADM")
        private Role role;

        @Past
        @Schema(description = "Data de nascimento do usuário", example = "yyyy-mm-dd")
        private LocalDate dataNascimento;

        @Schema(description = "Tipo de Empresa", example = "0 = PRIVADA")
        private TipoEmpresa tipoEmpresa;

        @Schema(description = "Status do Usuário", example = "1 = ATIVO")
        private Status status = Status.ATIVO;

        @Schema(description = "Pontuação do usuário PF", example = "10")
        private Integer pontuacao = 0;

        @Schema(description = "Moedas do usuário PF", example = "150")
        private Integer moedas = 0;
}

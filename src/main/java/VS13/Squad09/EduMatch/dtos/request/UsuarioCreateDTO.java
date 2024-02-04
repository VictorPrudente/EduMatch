package VS13.Squad09.EduMatch.dtos.request;


import VS13.Squad09.EduMatch.entities.*;
import VS13.Squad09.EduMatch.entities.enums.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCreateDTO {
        @Hidden
        @Positive
        @Schema(description = "Id do usuário", example = "1")
        private Integer idUsuario;

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
        @Schema(description = "Tipo de usuário", example = "PESSOA_FISICA/PESSOA-JURIDICA")
        private TipoUsuario tipoUsuario;

        @NotNull
        @Schema(description = "Permissão do usuário", example = "ADM")
        private Role role;

        @Past
        @Schema(description = "Data de nascimento do usuário", example = "yyyy-mm-dd")
        private LocalDate dataNascimento;

        @Schema(description = "Tipo de Empresa", example = "0 = PRIVADA")
        private TipoEmpresa tipoEmpresa;
}


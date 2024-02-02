package VS13.Squad09.EduMatch.dtos.request;

import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Dificuldade;
import VS13.Squad09.EduMatch.entities.enums.Trilha;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class CertificadoCreateDTO {
    @Positive(message = "O id não pode ser negativo")
    @Schema(description = "Id do Certificado", example = "1")
    private Integer id;

    @NotNull(message = "A trilha não pode estar vazia.")
    @Schema(description = "Trilha que será selecionada", required = true, example = "MATEMATICA")
    private Trilha trilha;


    @NotNull(message = "O tipo de dificuldade não pode estar vazia.")
    @Schema(description = "Dificuldade da trilha", example = "FACIL")
    private Dificuldade dificuldade;

}

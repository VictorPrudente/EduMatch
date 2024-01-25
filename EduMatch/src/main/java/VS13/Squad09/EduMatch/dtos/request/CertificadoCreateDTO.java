package VS13.Squad09.EduMatch.dtos.request;

import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Dificuldades;
import VS13.Squad09.EduMatch.entities.enums.Games;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
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
    private Games trilha;


    @NotNull(message = "A data de conclusão não pode estar vazia.")
    @PastOrPresent(message = "A data de conclusão não pode estar no futuro.")
    @Schema(description = "Data de conclusão da trilha", example = "yyyy-mm-dd")
    private LocalDateTime conclusao;

    @NotNull(message = "O tipo de dificuldade não pode estar vazia.")
    @Schema(description = "Dificuldade da trilha", example = "FACIL")
    private Dificuldades dificuldade;


    @NotNull(message = "O id do usuário não pode estar nulo.")
    @Schema(description = "Id do usuário cadastrado no banco de dados", required = true, example = "1")
    private Usuario usuario;
}

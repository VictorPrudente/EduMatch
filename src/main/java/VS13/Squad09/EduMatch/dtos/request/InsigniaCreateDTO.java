package VS13.Squad09.EduMatch.dtos.request;

import VS13.Squad09.EduMatch.entities.enums.Dificuldade;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.Trilha;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsigniaCreateDTO {

    @NotBlank
    @Schema(description = "imagem da insignia", example = "https://img.freepik.com/fotos-premium/arvore-psicodelica-ao-por-do-sol-hd-wallpaper_899449-86011.jpg")
    private String urlImagem;

    @NotBlank
    @Schema(description = "título da insignia", example = "Bronze")
    private String titulo;

    @NotBlank
    @Schema(description = "descrição da insignia", example = "Complete 1 trilha para ganhar a insignia de bronze")
    private String descricao;

    @NotNull
    @Positive
    @Schema(description = "pontuação da insignia", example = "10")
    private Integer pontuacao;

    @NotNull
    @Schema(description = "trilha da insignia", example = "0 = PORTUGUES")
    private Trilha trilha;

    @NotNull
    @Schema(description = "dificuldade da insignia", example = "0 = FACIL")
    private Dificuldade dificuldade;

    @NotNull
    @Schema(description = "status da insignia", example = "1 = ATIVO")
    private Status status;
}

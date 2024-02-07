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
    @Schema(description = "Url da imagem da insignia", example = "https://img.freepik.com/fotos-premium/arvore-psicodelica-ao-por-do-sol-hd-wallpaper_899449-86011.jpg")
    private String imagemUrl;

    @NotBlank
    @Schema(description = "título da insignia", example = "Português Fácil")
    private String titulo;

    @NotBlank(message = "A descrição da insígnia não pode ficar em branco.")
    @Schema(description = "descrição da insignia", example = "Esta insignia é recompensada a todos que completarem a prova de Português na dificuldade Fácil.")
    private String descricao;

}
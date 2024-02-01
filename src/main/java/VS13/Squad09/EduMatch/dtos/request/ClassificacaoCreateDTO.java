package VS13.Squad09.EduMatch.dtos.request;


import VS13.Squad09.EduMatch.entities.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassificacaoCreateDTO {

    @Positive(message = "O id não pode ser negativo")
    @Schema(description = "Id da classificação", example = "1")
    private Integer id;

    @NotBlank
    @Schema(description = "imagem da classificação", example = "https://img.freepik.com/fotos-premium/arvore-psicodelica-ao-por-do-sol-hd-wallpaper_899449-86011.jpg")
    private String urlImagem;

    @NotBlank
    @Schema(description = "título da classificação", example = "Bronze")
    private String titulo;

    @NotBlank
    @Schema(description = "descrição da classificação", example = "Complete 1 trilha para ganhar a classificação de bronze")
    private String descricao;

    @NotNull
    @Positive
    @Schema(description = "pontuação necessária para subir de classificação", example = "100")
    private Integer pontuacaoNecessaria;

    @NotNull
    @Schema(description = "status da classificação", example = "1 = ATIVO")
    private Status status;
}

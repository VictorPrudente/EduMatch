package VS13.Squad09.EduMatch.dtos.ranking;


import VS13.Squad09.EduMatch.entities.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankingCreateDTO {


    @URL(message = "Não é possível inserir um ranking no banco sem passar a URL da sua imagem. É necessário também, que a URL provida seja válida e única.")
    @Schema(description = "Imagem do Ranking", example = "https://img.freepik.com/fotos-premium/arvore-psicodelica-ao-por-do-sol-hd-wallpaper_899449-86011.jpg")
    private String urlImagem;

    @NotBlank(message = "O título do ranking não pode ficar em branco, assim como deve ser único.")
    @Schema(description = "Título do Ranking", example = "Bronze")
    private String titulo;

    @NotBlank(message = "A descrição do ranking não pode ser nula, assim como deve ser única.")
    @Schema(description = "Descrição do ranking", example = "O elo inicial de cada aventureiro. Jogue mais provas para subir sua pontuação e conquistar patamares maiores!")
    private String descricao;

    @NotNull(message = "A pontuação mínima do ranking não pode ser nula e deve ser única.")
    @Positive(message = "A pontuação mínima do ranking deve ser positiva e única.")
    @Schema(description = "Pontuação necessária para que o usuário possa subir de ranking.", example = "1000")
    private Integer pontuacaoNecessaria;
}

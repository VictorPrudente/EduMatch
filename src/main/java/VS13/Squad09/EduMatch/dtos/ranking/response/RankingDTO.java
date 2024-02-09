package VS13.Squad09.EduMatch.dtos.ranking.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RankingDTO {
    private Integer id;
    private String urlImagem;
    private String titulo;
    private String descricao;
    private Integer pontuacaoNecessaria;
}
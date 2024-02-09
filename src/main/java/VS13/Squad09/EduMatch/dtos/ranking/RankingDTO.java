package VS13.Squad09.EduMatch.dtos.ranking;

import VS13.Squad09.EduMatch.entities.Usuario;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RankingDTO {

    private Integer id;
    private String urlImagem;
    private String titulo;
    private String descricao;
    private List<Usuario> usuarios;
}

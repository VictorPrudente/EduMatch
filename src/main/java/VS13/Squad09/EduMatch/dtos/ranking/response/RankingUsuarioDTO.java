package VS13.Squad09.EduMatch.dtos.ranking.response;

import VS13.Squad09.EduMatch.entities.Usuario;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RankingUsuarioDTO extends RankingDTO{
    private List<Usuario> usuarios;
}

package VS13.Squad09.EduMatch.dtos.response;

import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Status;
import lombok.*;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RankingDTO {

    private Integer id;
    private String urlImagem;
    private String titulo;
    private String descricao;
    private Set<Usuario> usuarios;
}

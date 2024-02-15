package VS13.Squad09.EduMatch.dtos.response;

import VS13.Squad09.EduMatch.entities.enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RankingDTO {
    private Integer id;
    private String titulo;
    private String imagemUrl;
    private String descricao;
    private Integer pontuacaoNecessaria;
    private Status status;
    private List<UsuarioDTO> usuarios;


    public RankingDTO(Integer id, String titulo, String imagemUrl) {
        this.id = id;
        this.titulo = titulo;
        this.imagemUrl = imagemUrl;
    }

    public RankingDTO(Integer id, String titulo, String imagemUrl, String descricao, Integer pontuacaoNecessaria, Status status) {
        this.id = id;
        this.titulo = titulo;
        this.imagemUrl = imagemUrl;
        this.descricao = descricao;
        this.pontuacaoNecessaria = pontuacaoNecessaria;
        this.status = status;
    }
}
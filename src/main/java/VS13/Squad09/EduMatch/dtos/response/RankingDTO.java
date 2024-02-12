package VS13.Squad09.EduMatch.dtos.response;

import VS13.Squad09.EduMatch.dtos.usuario.response.UsuarioMinDTO;
import VS13.Squad09.EduMatch.entities.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String urlImagem;
    private String descricao;
    private Integer pontuacaoNecessaria;
    private Status status;
    private List<UsuarioMinDTO> usuarios;

    public RankingDTO(Integer id, String titulo, String urlImagem, String descricao, Integer pontuacaoNecessaria, Status status) {
        this.id = id;
        this.titulo = titulo;
        this.urlImagem = urlImagem;
        this.descricao = descricao;
        this.pontuacaoNecessaria = pontuacaoNecessaria;
        this.status = status;
    }
}
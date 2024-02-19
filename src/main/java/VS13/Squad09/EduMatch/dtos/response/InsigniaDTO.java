package VS13.Squad09.EduMatch.dtos.response;

import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InsigniaDTO {
    private Integer id;
    private String imagemUrl;
    private String titulo;
    private String descricao;
    private String tag;
    private Status status;
    private Set<Usuario> usuarios;


    public InsigniaDTO(Integer id, String imagemUrl, String titulo, String descricao, String tag, Status status) {
        this.id = id;
        this.imagemUrl = imagemUrl;
        this.titulo = titulo;
        this.descricao = descricao;
        this.tag = tag;
        this.status = status;
    }

    public InsigniaDTO(Integer id, String imagemUrl, String titulo) {
        this.id = id;
        this.imagemUrl = imagemUrl;
        this.titulo = titulo;
    }
}

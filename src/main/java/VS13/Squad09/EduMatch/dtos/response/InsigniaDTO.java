package VS13.Squad09.EduMatch.dtos.response;

import VS13.Squad09.EduMatch.entities.enums.Dificuldade;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.Trilha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsigniaDTO {
    private Integer id;
    private String urlImagem;
    private String titulo;
    private String descricao;
    private Integer pontuacao;
    private Trilha trilha;
    private Dificuldade dificuldade;
    private Status status;
    private LocalDateTime dataEmitida;
    private Integer idUsuario;
}

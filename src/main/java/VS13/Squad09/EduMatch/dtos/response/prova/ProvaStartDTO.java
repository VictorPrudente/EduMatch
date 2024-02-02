package VS13.Squad09.EduMatch.dtos.response.prova;

import VS13.Squad09.EduMatch.entities.Questao;
import VS13.Squad09.EduMatch.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvaStartDTO {

    private Integer id;

    private Set<Questao> questoes;

    private Usuario usuario;

    private LocalDateTime dataInicio;

    private Integer tempoLimite;

    private Integer totalQuestoes;
}

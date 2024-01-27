package VS13.Squad09.EduMatch.dtos.response;

import VS13.Squad09.EduMatch.dtos.request.QuestaoCreateDTO;
import VS13.Squad09.EduMatch.entities.enums.Dificuldades;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.Trilha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestaoDTO {
    private Integer id;
    private Integer pontos;
    private String pergunta;
    private String opcaoCerta;
    private Trilha trilha;
    private Dificuldades dificuldade;
    private Status status;

}

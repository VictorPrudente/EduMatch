package VS13.Squad09.EduMatch.entities;

import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.Dificuldades;
import VS13.Squad09.EduMatch.entities.enums.Trilha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Questao {

    private Integer id;
    private Integer pontos;
    private String pergunta;
    private String opcaoCerta;
    private Trilha trilha;
    private Dificuldades dificuldade;
    private Status status;

}

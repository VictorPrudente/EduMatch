package VS13.Squad09.EduMatch.dtos.response;

import VS13.Squad09.EduMatch.entities.Opcao;
import VS13.Squad09.EduMatch.entities.enums.Dificuldade;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.Trilha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestaoDTO {
    private Integer id;
    private String pergunta;
    private List<Opcao> opcoes;
    private String opcaoCerta;
    private Integer pontos;
    private Trilha trilha;
    private Dificuldade dificuldade;
    private Status status;
}
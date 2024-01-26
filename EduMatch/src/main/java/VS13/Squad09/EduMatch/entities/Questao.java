package VS13.Squad09.EduMatch.entities;

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

    public Questao(Integer id, String pergunta, String opcaoCerta, Dificuldades dificuldade, Trilha trilha) {
        this.id = id;
        this.pergunta = pergunta;
        this.opcaoCerta = opcaoCerta;
        this.dificuldade = dificuldade;
        this.trilha = trilha;
        this.pontos = dificuldade.getNivel() * 3;
    }

}

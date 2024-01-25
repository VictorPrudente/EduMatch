package VS13.Squad09.EduMatch.dtos.request;

import VS13.Squad09.EduMatch.entities.enums.Dificuldades;
import VS13.Squad09.EduMatch.entities.enums.Trilha;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestaoCreateDTO {

    private Integer id;

    private Integer pontos;

    @NotNull(message = "A pergunta não pode estar vazia.")
    @NotBlank(message = "A pergunta não pode estar em branco.")
    private String pergunta;

    @NotNull(message = "A opção certa não pode ser nula.")
    @NotBlank(message = "A opção certa não pode estar em branco.")
    private String opcaoCerta;

    @NotNull(message = "A dificuldade não pode estar nulo.")
    private Dificuldades dificuldade;

    @NotNull(message = "A trilha não pode estar nula.")
    private Trilha trilha;

    public QuestaoCreateDTO(Integer id, String pergunta, String opcaoCerta, Dificuldades dificuldade, Trilha trilha) {
        this.id = id;
        this.pergunta = pergunta;
        this.opcaoCerta = opcaoCerta;
        this.dificuldade = dificuldade;
        this.trilha = trilha;
        this.pontos = dificuldade.getNivel() * 3;
    }
}
package VS13.Squad09.EduMatch.dtos.request;

import VS13.Squad09.EduMatch.entities.enums.Dificuldade;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.Trilha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    private Dificuldade dificuldade;

    @NotNull(message = "A trilha não pode estar nula.")
    private Trilha trilha;
}
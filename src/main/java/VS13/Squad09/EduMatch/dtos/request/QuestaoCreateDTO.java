package VS13.Squad09.EduMatch.dtos.request;

import VS13.Squad09.EduMatch.entities.Opcao;
import VS13.Squad09.EduMatch.entities.enums.Dificuldade;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.Trilha;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestaoCreateDTO {


    @NotBlank(message = "A pergunta não pode estar em branco.")
    private String pergunta;

    @NotBlank(message = "As opções da questão não devem estar em branco.")
    @Size(min = 5, max = 5, message = "Uma questão deve conter uma lista de 5 opções.")
    private List<Opcao> opcoes = new ArrayList<>();

    @NotBlank(message = "A opção certa não pode estar em branco.")
    private String opcaoCerta;

    @NotNull(message = "Uma prova precisa de um nível de dificuldade. Ela não pode estar nula.")
    private Dificuldade dificuldade;

    @NotNull(message = "Uma prova precisa de uma trilha. Ela não pode estar nula.")
    private Trilha trilha;
}
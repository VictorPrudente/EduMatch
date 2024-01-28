package VS13.Squad09.EduMatch.dtos.request;

import VS13.Squad09.EduMatch.entities.enums.Dificuldade;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.Trilha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsigniaCreateDTO {

    private Integer id;

    @NotBlank
    private String urlImagem;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotNull
    @Positive
    private Integer pontuacao;

    @NotNull
    private Trilha trilha;

    @NotNull
    private Dificuldade dificuldade;

    @NotNull
    private Status status;

}

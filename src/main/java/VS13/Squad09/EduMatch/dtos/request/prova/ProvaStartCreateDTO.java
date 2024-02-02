package VS13.Squad09.EduMatch.dtos.request.prova;


import VS13.Squad09.EduMatch.entities.enums.Dificuldade;
import VS13.Squad09.EduMatch.entities.enums.Trilha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvaStartCreateDTO {

    @NotNull(message = "Uma prova precisa de um jogador!")
    private Integer idUsuario;

    @NotNull
    Trilha trilha;

    @NotNull
    Dificuldade dificuldade;

}

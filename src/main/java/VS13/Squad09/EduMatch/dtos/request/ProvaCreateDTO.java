package VS13.Squad09.EduMatch.dtos.request;


import VS13.Squad09.EduMatch.entities.Questao;
import VS13.Squad09.EduMatch.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvaCreateDTO {

    @NotNull(message = "Uma prova precisa de um jogador!")
    private Integer idUsuario;

}

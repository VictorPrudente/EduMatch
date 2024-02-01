package VS13.Squad09.EduMatch.dtos.request.prova;


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


}

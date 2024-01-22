package VS13.Squad09.EduMatch.dtos.request;

import VS13.Squad9.EduMatch.entities.Usuario;
import VS13.Squad9.EduMatch.entities.enums.Games;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public class CertificadoCreateDTO {

    private Integer id;
    @NotNull(message = "A trilha não pode estar vazia.")
    private Games trilha;

    @NotNull(message = "A data de conclusão não pode estar vazia.")
    @PastOrPresent(message = "A data de conclusão não pode estar no futuro.")
    private LocalDateTime conclusao = LocalDateTime.now();
    @NotNull(message = "O usuário não pode estar nulo.")
    private Usuario usuario;

}

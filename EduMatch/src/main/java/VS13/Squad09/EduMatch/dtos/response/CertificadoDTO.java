package VS13.Squad09.EduMatch.dtos.response;

import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Dificuldades;
import VS13.Squad09.EduMatch.entities.enums.Games;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificadoDTO {
    private Games trilha;
    private LocalDateTime conclusao = LocalDateTime.now();
    private Usuario usuario;
    private Dificuldades dificuldade;
}

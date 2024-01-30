package VS13.Squad09.EduMatch.dtos.response;

import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Dificuldade;

import VS13.Squad09.EduMatch.entities.enums.Trilha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificadoDTO {
    private Trilha trilha;
    private LocalDateTime conclusao;
    private Usuario usuario;
    private Dificuldade dificuldade;
}

package VS13.Squad09.EduMatch.dtos;

import VS13.Squad09.EduMatch.entities.enums.Dificuldade;
import VS13.Squad09.EduMatch.entities.enums.Trilha;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CertificadoRelatorioDTO {

    private Integer id_certificado;

    private Trilha trilha;

    private LocalDateTime conclusao = LocalDateTime.now();

    private Dificuldade dificuldade;
}

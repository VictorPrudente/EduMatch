package VS13.Squad09.EduMatch.dtos.response;

import VS13.Squad09.EduMatch.entities.Questao;
import VS13.Squad09.EduMatch.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvaDTO {

    private Integer id;

    private List<Questao> questoes;

    private Usuario usuario;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFinal;

    private Integer totalAcertos;
}

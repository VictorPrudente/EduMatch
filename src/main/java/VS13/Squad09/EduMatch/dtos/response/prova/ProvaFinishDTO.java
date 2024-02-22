package VS13.Squad09.EduMatch.dtos.response.prova;

import VS13.Squad09.EduMatch.entities.Questao;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Resultado;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProvaFinishDTO {

    private Integer id;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFinal;

    private Integer pontos;

    private Double nota;

    private Integer totalAcertos;

    private Integer totalQuestoes;

    private Resultado resultado;

    private List<Questao> questoes;

    private Usuario usuario;

}

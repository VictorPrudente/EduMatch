package VS13.Squad09.EduMatch.dtos.request;


import VS13.Squad09.EduMatch.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassificacaoCreateDTO {

    private Integer id;
    private String urlImagem;
    private String titulo;
    private String descricao;
    private Integer pontuacaoNecessaria;
    private Status status;
}

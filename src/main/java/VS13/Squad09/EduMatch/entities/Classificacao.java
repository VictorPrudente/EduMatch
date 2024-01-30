package VS13.Squad09.EduMatch.entities;

import VS13.Squad09.EduMatch.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Classificacao {

    private Integer id;
    private String urlImagem;
    private String titulo;
    private String descricao;
    private Integer pontuacaoNecessaria;
    private Status status;
}

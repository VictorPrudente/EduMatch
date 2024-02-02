package VS13.Squad09.EduMatch.dtos.response;


import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.TipoDistintivo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DistintivoDTO {

    private Integer id;

    private String urlImagem;

    private String titulo;

    private String descricao;

    private TipoDistintivo tipo;
}

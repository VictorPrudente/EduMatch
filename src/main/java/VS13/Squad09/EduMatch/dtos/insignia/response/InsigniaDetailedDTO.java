package VS13.Squad09.EduMatch.dtos.insignia.response;


import VS13.Squad09.EduMatch.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsigniaDetailedDTO {
    private Integer id;
    private String imagemUrl;
    private String titulo;
    private String descricao;
    private Status status;
}

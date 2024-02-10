package VS13.Squad09.EduMatch.dtos.insignia.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsigniaDTO {
    private Integer id;
    private String titulo;
    private String imagemUrl;
}

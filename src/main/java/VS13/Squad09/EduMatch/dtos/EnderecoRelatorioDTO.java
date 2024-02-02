package VS13.Squad09.EduMatch.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRelatorioDTO {

        private String cep;
        private String cidade;
        private String estado;
        private String pais;
}


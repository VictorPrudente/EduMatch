package VS13.Squad09.EduMatch.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioECertificadoRelatorioDTO{

    private Integer idUsuario;
    private String nome;
    private String email;
    private List<CertificadoRelatorioDTO> certificadoUsuario = new ArrayList<>();

        public UsuarioECertificadoRelatorioDTO(Integer idUsuario, String nome, String email) {
            this.idUsuario = idUsuario;
            this.nome = nome;
            this.email = email;
        }
}

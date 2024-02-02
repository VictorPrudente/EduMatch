package VS13.Squad09.EduMatch.dtos;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCompletoRelatorioDTO {

        private Integer idUsuario;
        private String nome;
        private String email;
        private List<ContatoRelatorioDTO> contatosUsuario = new ArrayList<>();
        private List<EnderecoRelatorioDTO> enderecosUsuario = new ArrayList<>();

        public UsuarioCompletoRelatorioDTO(Integer idUsuario, String nome, String email) {
            this.idUsuario = idUsuario;
            this.nome = nome;
            this.email = email;
        }
}


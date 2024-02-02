package VS13.Squad09.EduMatch.dtos.response;

import VS13.Squad09.EduMatch.entities.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaFisicaDTO {
    private Integer id;
    private String email;
    private String nome;
    private String sobrenome;
    private String CPF;
    private TipoUsuario tipoUsuario;
    private LocalDate dataNascimento;
    private Integer pontuacao;
    private Integer moedas;
}

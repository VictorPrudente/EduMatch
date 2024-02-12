package VS13.Squad09.EduMatch.dtos.response;

import VS13.Squad09.EduMatch.entities.enums.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {
    private Integer idUsuario;
    private String email;
    private String nome;
    private String sobrenome;
    private String CPF;
    private String CNPJ;
    private TipoUsuario tipoUsuario;
    private Status status;
    private LocalDate dataNascimento;
    private Integer pontuacao;
    private Integer moedas;
    private Elo elo;

}

package VS13.Squad09.EduMatch.dtos.response;

import VS13.Squad09.EduMatch.entities.*;
import VS13.Squad09.EduMatch.entities.enums.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.Nulls;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

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
    private TipoEmpresa tipoEmpresa;
    private Integer moedas;
    private Elo elo;

}

package VS13.Squad09.EduMatch.entities;

import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.TipoUsuario;
import VS13.Squad09.EduMatch.entities.enums.Role;
import VS13.Squad09.EduMatch.entities.enums.TipoEmpresa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private Integer id;
    private String email;
    private String senha;
    private String nome;
    private TipoUsuario tipoUsuario;
    private String CPF;
    private String sobrenome;
    private LocalDate dataNascimento;
    private Integer pontuacao;
    private Integer moedas;
    private String CNPJ;
    private TipoEmpresa tipoEmpresa;
    private Role role;
    private Status status;
}

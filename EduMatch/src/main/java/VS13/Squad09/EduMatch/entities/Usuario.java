package VS13.Squad09.EduMatch.entities;

import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.TipoDocumento;
import VS13.Squad09.EduMatch.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario {
    private Integer id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String CPF;
    private String CNPJ;
    private TipoDocumento tipoDocumento;
    private Role role;
    private Status status;
    private LocalDate dataNascimento;
    private Integer pontuacao;
    private Integer idEscola;
    private Integer idEmpresa;
}

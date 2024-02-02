package VS13.Squad09.EduMatch.entities;

import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.TipoUsuario;
import VS13.Squad09.EduMatch.entities.enums.Role;
import VS13.Squad09.EduMatch.entities.enums.TipoEmpresa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "USUARIO")
public class Usuario {

    @Id
    @Column(name = "ID_USUARIO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
    @SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO", allocationSize = 1)
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
    @JsonIgnore
    @OneToOne(mappedBy = "usuario")
    private Prova prova;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Usuario usuario = (Usuario) object;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

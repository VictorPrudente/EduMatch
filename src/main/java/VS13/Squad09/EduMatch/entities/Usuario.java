package VS13.Squad09.EduMatch.entities;

import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.TipoUsuario;
import VS13.Squad09.EduMatch.entities.enums.Role;
import VS13.Squad09.EduMatch.entities.enums.TipoEmpresa;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="USUARIO")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
    @SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    @Column(name = "id_pessoa")
    private Integer idUsuario;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo_usuario")
    private TipoUsuario tipoUsuario;

    @Column(name = "cpf")
    private String CPF;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "pontuacao")
    private Integer pontuacao;

    @Column(name = "moedas")
    private Integer moedas;

    @Column(name = "cnpj")
    private String CNPJ;

    @Column(name = "tipo_empresa")
    private TipoEmpresa tipoEmpresa;

    @Column(name = "role")
    private Role role;

    @Column(name = "status")
    private Status status;

    @OneToOne(mappedBy = "usuario")
    @Column(name = "prova")
    private Prova prova;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contato", referencedColumnName = "id_contato")
    private Contato contato;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
    private Endereco endereco;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Insignia> insignias;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Certificado> certificados;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ranking> rankings;
}

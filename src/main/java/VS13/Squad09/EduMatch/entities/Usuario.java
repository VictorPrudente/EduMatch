package VS13.Squad09.EduMatch.entities;

import VS13.Squad09.EduMatch.entities.enums.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "USUARIO")
public class Usuario {

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
    @SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO", allocationSize = 1)
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

    @Column(name = "status")
    private Status status;
  
    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private Set<Prova> prova;

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
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Insignia> insignias;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Certificado> certificados;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_RANKING", referencedColumnName = "ID_RANKING")
    private Ranking ranking;

    @Column(name = "ELO")
    private Elo elo;


    public void pontuar(Integer pontos){
        this.pontuacao += pontos;
    }
  
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Usuario usuario = (Usuario) object;
        return Objects.equals(idUsuario, usuario.idUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario);
    }
}

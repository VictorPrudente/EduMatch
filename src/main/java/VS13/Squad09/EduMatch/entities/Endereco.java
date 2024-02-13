package VS13.Squad09.EduMatch.entities;

import VS13.Squad09.EduMatch.entities.enums.TipoDeEndereco;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ENDERECO")
public class Endereco {
    @Id
    @Column(name = "ID_ENDERECO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ENDERECO")
    @SequenceGenerator(name = "SEQ_ENDERECO", sequenceName = "SEQ_ENDERECO", allocationSize = 1)
    private Integer id;
    @Column(name = "LOGRADOURO")
    private String logradouro;
    @Column(name = "NUMERO")
    private Integer numero;
    @Column(name = "COMPLEMENTO")
    private String complemento;
    @Column(name = "TIPO_ENDERECO")
    private TipoDeEndereco tipoDeEndereco;
    @Column(name = "CEP")
    private String cep;
    @Column(name = "CIDADE")
    private String cidade;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "PAIS")
    private String pais;
    @JoinColumn(name = "ID_USUARIO")
    private Integer idUsuario;
}
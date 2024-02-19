package VS13.Squad09.EduMatch.entities;
import VS13.Squad09.EduMatch.entities.enums.TipoDeContato;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CONTATO")
public class Contato {
    @Id
    @Column(name = "ID_CONTATO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTATO")
    @SequenceGenerator(name = "SEQ_CONTATO", sequenceName = "SEQ_CONTATO", allocationSize = 1)
    private Integer id;
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "TELEFONE")
    private String telefone;
    @Column(name = "TIPO_CONTATO")
    private TipoDeContato tipo;
    @JoinColumn(name = "ID_USUARIO")
    private Integer idUsuario;
}
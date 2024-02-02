package VS13.Squad09.EduMatch.entities;
import VS13.Squad09.EduMatch.entities.enums.TipoDeContato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "CONTATO")
@AllArgsConstructor
public class Contato {
    @Id
    @Column(name = "ID_CONTATO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTATO")
    private Integer id;
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "TELEFONE")
    private String telefone;
    @Column(name = "TIPO")
    private TipoDeContato tipo;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    private Usuario usuario;
}

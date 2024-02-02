package VS13.Squad09.EduMatch.entities;

import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.TipoDistintivo;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "DISTINTIVOS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Distintivo {

    @Id
    @Column(name = "ID_DISTINTIVO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DISTINTIVO")
    @SequenceGenerator(name = "SEQ_DISTINTIVO", sequenceName = "SEQ_DISTINTIVO", allocationSize = 1)
    private Integer id;

    @Column(name = "IMAGEM_URL")
    private String urlImagem;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "STATUS")
    private Status status;

    @Column(name = "TIPO_DISTINTIVO")
    private TipoDistintivo tipo;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Distintivo that = (Distintivo) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

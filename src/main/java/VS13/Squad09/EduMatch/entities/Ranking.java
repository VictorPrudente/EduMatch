package VS13.Squad09.EduMatch.entities;


import VS13.Squad09.EduMatch.entities.enums.Status;
import lombok.*;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "RANKING")
public class Ranking {

    @Id
    @Column(name = "ID_RANKING", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RANKING")
    @SequenceGenerator(name = "SEQ_RANKING", sequenceName = "SEQ_RANKING", allocationSize = 1)
    private Integer id;

    @Column(name = "TITULO", nullable = false, unique = true)
    private String titulo;

    @Column(name = "IMAGEM_URL", nullable = false, unique = true)
    private String imagemUrl;

    @Column(name = "DESCRICAO", nullable = false, unique = true)
    private String descricao;

    @Column(name = "PONTUACAO_NECESSARIA", nullable = false, unique = true)
    private Integer pontuacaoNecessaria;

    @Column(name = "STATUS", nullable = false)
    private Status status;

    @OrderBy("pontuacao DESC")
    @OneToMany(mappedBy = "ranking", cascade = CascadeType.ALL)
    private List<Usuario> usuarios;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Ranking ranking = (Ranking) object;
        return Objects.equals(id, ranking.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

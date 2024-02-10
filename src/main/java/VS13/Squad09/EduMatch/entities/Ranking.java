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
    @Column(name = "ID_RANKING")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RANKING")
    @SequenceGenerator(name = "SEQ_RANKING", sequenceName = "SEQ_RANKING", allocationSize = 1)
    private Integer id;

    @OrderBy("pontuacao DESC")
    @OneToMany(mappedBy = "ranking", cascade = CascadeType.ALL)
    private List<Usuario> usuarios;

    @Column(name = "PONTUACAO_NECESSARIA")
    private Integer pontuacaoNecessaria;

    @Column(name = "IMAGEM_URL")
    private String urlImagem;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "STATUS")
    private Status status;

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

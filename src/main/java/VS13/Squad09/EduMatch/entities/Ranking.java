package VS13.Squad09.EduMatch.entities;


import VS13.Squad09.EduMatch.entities.enums.Elo;
import VS13.Squad09.EduMatch.entities.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

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

    @OneToMany(mappedBy = "ranking", cascade = CascadeType.ALL)
    private Set<Usuario> usuarios;

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
        if (!super.equals(object)) return false;
        Ranking ranking = (Ranking) object;
        return Objects.equals(pontuacaoNecessaria, ranking.pontuacaoNecessaria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pontuacaoNecessaria);
    }
}

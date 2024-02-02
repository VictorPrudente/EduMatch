package VS13.Squad09.EduMatch.entities;


import VS13.Squad09.EduMatch.entities.enums.Elo;
import lombok.*;
import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "RANKING")
public class Ranking extends Distintivo {

    @OneToMany(mappedBy = "ranking", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Usuario> usuarios;

    @Column(name = "PONTUACAO_NECESSARIA")
    private Integer pontuacaoNecessaria;

    @Column(name = "ELO")
    private Elo elo;

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

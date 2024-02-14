package VS13.Squad09.EduMatch.entities;

import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.Dificuldade;
import VS13.Squad09.EduMatch.entities.enums.Trilha;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "QUESTAO")
public class Questao {

    @Id
    @Column(name = "ID_QUESTAO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_QUESTAO")
    @SequenceGenerator(name = "SEQ_QUESTAO", sequenceName = "SEQ_QUESTAO", allocationSize = 1)
    private Integer id;

    @Column(name = "PERGUNTA")
    private String pergunta;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "LISTA_OPCOES", joinColumns = @JoinColumn(name = "ID_QUESTAO", nullable = false))
    private List<Opcao> opcoes = new ArrayList<>();

    @Column(name = "OPCAO_CORRETA")
    private String opcaoCerta;

    @Column(name = "PONTOS")
    private Integer pontos;

    @Column(name = "TRILHA")
    private Trilha trilha;

    @Column(name = "DIFICULDADE")
    private Dificuldade dificuldade;

    @Column(name = "STATUS")
    private Status status;

    @JsonIgnore
    @ManyToMany(mappedBy = "questoes")
    private List<Prova> prova;

    public void shuffleOpcoes(){
        Collections.shuffle(opcoes);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Questao questao = (Questao) object;
        return Objects.equals(id, questao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

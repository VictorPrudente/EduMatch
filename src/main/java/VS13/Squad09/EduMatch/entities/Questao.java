package VS13.Squad09.EduMatch.entities;

import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.Dificuldade;
import VS13.Squad09.EduMatch.entities.enums.Trilha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
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

    public void shuffleOpcoes(){
        Collections.shuffle(opcoes);
    }
}

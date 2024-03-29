package VS13.Squad09.EduMatch.entities;


import VS13.Squad09.EduMatch.entities.enums.Resultado;
import VS13.Squad09.EduMatch.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PROVA")
public class Prova {

    @Id
    @Column(name = "ID_PROVA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROVA")
    @SequenceGenerator(name = "SEQ_PROVA", sequenceName = "SEQ_PROVA", allocationSize = 1)
    private Integer id;

    @ManyToMany
    @JoinTable(
            name = "PROVA_QUESTAO",
            joinColumns = @JoinColumn(name = "ID_PROVA"),
            inverseJoinColumns = @JoinColumn(name = "ID_QUESTAO"))
    private List<Questao> questoes;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "LISTA_RESPOSTAS", joinColumns = @JoinColumn(name = "ID_PROVA", nullable = false))
    private List<Resposta> respostas = new ArrayList<>();

    @Column(name = "HORA_INICIO")
    private LocalDateTime dataInicio;

    @Column(name = "HORA_FINAL")
    private LocalDateTime dataFinal;

    @Column(name = "TEMPO_LIMITE")
    private Integer tempoLimite;

    @Column(name = "PONTOS")
    private Integer pontos;

    @Column(name = "TOTAL_ACERTOS")
    private Integer totalAcertos;

    @Column(name = "TOTAL_QUESTOES")
    private Integer totalQuestoes;

    @Column(name = "NOTA")
    private Double nota;

    @Column(name = "RESULTADO")
    private Resultado resultado;

    @Column(name = "STATUS")
    private Status status;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Prova prova = (Prova) object;
        return Objects.equals(id, prova.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

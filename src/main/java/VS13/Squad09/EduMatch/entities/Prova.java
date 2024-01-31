package VS13.Squad09.EduMatch.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
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
            joinColumns = @JoinColumn(name = "PROVA_ID"),
            inverseJoinColumns = @JoinColumn(name = "QUESTAO_ID"))
    private List<Questao> questoes;

    @OneToOne
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuario;

    @Column(name = "INICIO")
    private LocalDateTime dataInicio;

    @Column(name = "FINAL")
    private LocalDateTime dataFinal;

    @Column(name = "TOTAL_ACERTOS")
    private Integer totalAcertos;
}

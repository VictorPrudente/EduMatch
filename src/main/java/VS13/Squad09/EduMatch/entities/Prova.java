package VS13.Squad09.EduMatch.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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
            joinColumns = @JoinColumn(name = "ID_PROVA"),
            inverseJoinColumns = @JoinColumn(name = "ID_QUESTAO"))
    private List<Questao> questoes;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "LISTA_RESPOSTAS", joinColumns = @JoinColumn(name = "ID_PROVA", nullable = false))
    private List<Resposta> respostas;

    @OneToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @Column(name = "HORA_INICIO")
    private LocalDateTime dataInicio;

    @Column(name = "HORA_FINAL")
    private LocalDateTime dataFinal;
    public void shuffleOpcoes(){
        for(Questao questao : questoes){
            questao.shuffleOpcoes();
        }
    }
}

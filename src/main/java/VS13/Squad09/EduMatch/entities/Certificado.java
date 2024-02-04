package VS13.Squad09.EduMatch.entities;

import VS13.Squad09.EduMatch.entities.enums.Dificuldade;

import VS13.Squad09.EduMatch.entities.enums.Trilha;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CERTIFICADO")
public class Certificado {

    @Id
    @Column(name = "ID_CERTIFICADO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CERTIFICADO")
    @SequenceGenerator(name = "SEQ_CERTIFICADO", sequenceName = "SEQ_CERTIFICADO", allocationSize = 1)
    private Integer id_certificado;

    @Column(name = "trilha")
    private Trilha trilha;

    @Column(name = "dificuldade")
    private Dificuldade dificuldade;

    @Column(name = "data_emitida")
    private LocalDateTime conclusao = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    private Usuario usuario;

}



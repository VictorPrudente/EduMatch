package VS13.Squad09.EduMatch.entities;

import VS13.Squad09.EduMatch.entities.enums.Dificuldade;

import VS13.Squad09.EduMatch.entities.enums.Trilha;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


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

    @Column(name = "TRILHA")
    private Trilha trilha;

    @Column(name = "DIFICULDADE")
    private Dificuldade dificuldade;

    @Column(name = "CONCLUSAO")
    private LocalDateTime conclusao = LocalDateTime.now();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    private Usuario usuario;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Certificado that = (Certificado) object;
        return Objects.equals(id_certificado, that.id_certificado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_certificado);
    }
}



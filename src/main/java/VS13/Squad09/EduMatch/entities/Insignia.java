package VS13.Squad09.EduMatch.entities;

import VS13.Squad09.EduMatch.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "INSIGNIA")
public class Insignia {


    @Id
    @Column(name = "ID_INSIGNIA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INSIGNIA")
    @SequenceGenerator(name = "SEQ_INSIGNIA", sequenceName = "SEQ_INSIGNIA", allocationSize = 1)
    private Integer id;

    @Column(name = "DATA_EMITIDA")
    private LocalDateTime dataEmitida;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    private Usuario usuario;

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
        Insignia insignia = (Insignia) object;
        return Objects.equals(usuario, insignia.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), usuario);
    }
}

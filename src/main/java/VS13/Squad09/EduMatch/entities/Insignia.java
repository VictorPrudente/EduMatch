package VS13.Squad09.EduMatch.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "INSIGNIA")
public class Insignia extends Distintivo {

    @Column(name = "DATA_EMITIDA")
    private LocalDateTime dataEmitida;

    @OneToOne
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    private Usuario usuario;

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

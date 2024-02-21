package VS13.Squad09.EduMatch.entities;

import VS13.Squad09.EduMatch.entities.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    @Column(name = "IMAGEM_URL")
    private String imagemUrl;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "TAG")
    private String tag;

    @Column(name = "STATUS")
    private Status status;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "USUARIO_INSIGNIA",
            joinColumns = @JoinColumn(name = "ID_INSIGNIA"),
            inverseJoinColumns = @JoinColumn(name = "ID_USUARIO"))
    private Set<Usuario> usuarios = new HashSet<>();

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Insignia insignia = (Insignia) object;
        return Objects.equals(id, insignia.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

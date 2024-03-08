package VS13.Squad09.EduMatch.entities;


import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Opcao implements Serializable {

    @Column(name = "OPCOES")
    private String opcao;

}

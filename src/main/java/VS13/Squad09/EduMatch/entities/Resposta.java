package VS13.Squad09.EduMatch.entities;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Resposta implements Serializable {

    @Column(name = "RESPOSTA")
    private String resposta;

}

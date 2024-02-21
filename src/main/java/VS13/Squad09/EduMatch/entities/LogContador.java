package VS13.Squad09.EduMatch.entities;

import VS13.Squad09.EduMatch.entities.enums.TipoLog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogContador {
    @Id
    private TipoLog tipoLog;
    private Integer quantidade;
    }

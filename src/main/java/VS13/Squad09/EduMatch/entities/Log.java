package VS13.Squad09.EduMatch.entities;

import VS13.Squad09.EduMatch.entities.enums.TipoLog;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Getter
@Setter
@Document(collection = "log")
public class Log {
        @Id
        private String id;
        @Enumerated(EnumType.STRING)
        private TipoLog tipoLog;
        private String descricao;
        private String data;
    }
}

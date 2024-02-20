package VS13.Squad09.EduMatch.entities;

import VS13.Squad09.EduMatch.entities.enums.TipoLog;
import VS13.Squad09.EduMatch.entities.enums.TipoOperacao;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "log")
public class Log {
        @Id
        private String id;
        @Enumerated(EnumType.STRING)
        private TipoLog tipoLog;
        @Enumerated(EnumType.STRING)
        private TipoOperacao tipoOperacao;
        private String descricao;
        private LocalDate data;

        public Log (TipoLog tipoLog, TipoOperacao tipoOperacao, String descricao, LocalDate data){
                this.tipoLog = tipoLog;
                this.tipoOperacao = tipoOperacao;
                this.descricao = descricao;
                this.data = data;
        }
    }


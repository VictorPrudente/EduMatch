package VS13.Squad09.EduMatch.dtos;

import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Dificuldade;
import VS13.Squad09.EduMatch.entities.enums.Trilha;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CertificadoRelatorioDTO {

    private Integer id_certificado;

    private Trilha trilha;

    private LocalDateTime conclusao = LocalDateTime.now();

    private Dificuldade dificuldade;
}

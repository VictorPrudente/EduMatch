package VS13.Squad09.EduMatch.entities;

import VS13.Squad09.EduMatch.entities.enums.Dificuldades;

import VS13.Squad09.EduMatch.entities.enums.Trilha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Certificado {

    private Trilha trilha;

    private LocalDateTime conclusao = LocalDateTime.now();

    private Usuario usuario;

    private Dificuldades dificuldade;

    private Integer id;
}
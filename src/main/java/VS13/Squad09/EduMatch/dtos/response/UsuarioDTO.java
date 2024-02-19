package VS13.Squad09.EduMatch.dtos.response;

import VS13.Squad09.EduMatch.entities.enums.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {
    private Integer idUsuario;
    private String email;
    private String nome;
    private String sobrenome;
    private String CPF;
    private String CNPJ;
    private TipoUsuario tipoUsuario;
    private Status status;
    private LocalDate dataNascimento;
    private Integer pontuacao;
    private Integer moedas;
    private Elo elo;
    private String fotoUrl;
    private Integer posicao;

    //RANKING
    public UsuarioDTO(Integer idUsuario, String nome, String sobrenome, Integer pontuacao, String fotoUrl) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.pontuacao = pontuacao;
        this.fotoUrl = fotoUrl;
    }

    //EMPRESA
    public UsuarioDTO(Integer idUsuario, String email, String nome, String CNPJ, String fotoUrl) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.nome = nome;
        this.CNPJ = CNPJ;
        this.fotoUrl = fotoUrl;
    }
}
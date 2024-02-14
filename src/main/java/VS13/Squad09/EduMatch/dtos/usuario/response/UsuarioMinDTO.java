package VS13.Squad09.EduMatch.dtos.usuario.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioMinDTO {

    private Integer idUsuario;
    private String nome;
    private String sobrenome;
    private Integer pontuacao;
    private Integer posicao;

    public UsuarioMinDTO(Integer idUsuario, String nome, String sobrenome, Integer pontuacao) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.pontuacao = pontuacao;
    }
}

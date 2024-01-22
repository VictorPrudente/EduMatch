package VS13.Squad09.EduMatch.entities;

import VS13.Squad9.EduMatch.entities.Certificado;
import VS13.Squad9.EduMatch.entities.Contato;
import VS13.Squad9.EduMatch.entities.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    private Integer id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String CPF;
    private Integer idade;
    private Integer pontuacao;
    private Endereco endereco;
    private Contato contato;
    private List<Certificado> certificados = new ArrayList<>();
    private Integer id_escola;
    private Integer id_empresa;

}

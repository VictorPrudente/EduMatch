package VS13.Squad09.EduMatch.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
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

    @Override
    public String toString() {
        return String.format("""
                Nome: %s %s
                Idade: %d
                Pontucação: %d""", nome, sobrenome, idade, pontuacao);
    }

    public String imprimirDados(){
        return String.format("""
                Nome: %s %s
                CPF: %s
                Idade: %d
                Pontuação: %d""", nome, sobrenome, CPF, idade, pontuacao);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

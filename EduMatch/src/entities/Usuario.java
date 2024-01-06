package entities;

import java.util.List;
import java.util.Objects;


public class Usuario {

    private Integer id;
    private String nome;
    private String sobrenome;
    private String CPF;
    private Integer idade;
    private Integer pontuacao;
    public Usuario() {
    }

    public Usuario(String nome, String sobrenome, String CPF,
                   Integer idade, Integer pontuacao) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.CPF = CPF;
        this.idade = idade;
        this.pontuacao = pontuacao;
    }

    public boolean temAutorizacaoDosPais(){
        return false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao){
        this.pontuacao += pontuacao;
    }
    @Override
    public String toString() {
        return String.format("""
                -=-=-=-=-=-=-=-=-=-
                ID: %d
                Nome: %s %s
                Idade: %d
                Pontucação: %d""", id, nome, sobrenome, idade, pontuacao);
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

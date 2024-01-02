package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {
    private String nome;
    private String sobrenome;
    private String email;
    private String login;
    private String senha;
    private Integer tipoDeUser;
    private String CPF;
    private Integer idade;
    private Endereco endereco;
    private Integer pontuacao;

    public Usuario() {
    }

    public Usuario(String nome, String sobrenome, String login, String senha, Integer tipoDeUser, String CPF,
                   Integer idade, Endereco endereco, Integer pontuacao) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.login = login;
        this.senha = senha;
        this.tipoDeUser = tipoDeUser;
        this.CPF = CPF;
        this.idade = idade;
        this.endereco = endereco;
        this.pontuacao = pontuacao;
    }

    public void aumentarPontuacao(int pontos){
        this.pontuacao += pontos;
    }

    public boolean temAutorizacaoDosPais(){
        return false;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getTipoDeUser() {
        return tipoDeUser;
    }

    public void setTipoDeUser(Integer tipoDeUser) {
        this.tipoDeUser = tipoDeUser;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return
                "nome= " + nome + '\'' +
                        ", sobrenome= " + sobrenome + '\'' +
                        ", email= " + email + '\'' +
                        ", login='" + login + '\'' +
                        ", senha='" + senha + '\'' +
                        ", tipoDeUser=" + tipoDeUser +
                        ", CPF='" + CPF + '\'' +
                        ", idade=" + idade +
                        ", endereco=" + endereco +
                        ", pontuacao=" + pontuacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(CPF, usuario.CPF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CPF);
    }
}

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
    private EduMatch.src.entities.Escola escola;
    private List<Endereco> enderecos;
    private List<Contato> contatos;
    private List<Certificado> certificados;

    public Usuario() {
    }


    public Usuario(int id, String nome, String sobrenome, String CPF,
                   Integer idade) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.CPF = CPF;
        this.idade = idade;
        this.pontuacao = 0;
    }

    public boolean temAutorizacaoDosPais(){
        return false;
    }

    public Integer getId() {
        return id;
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

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public List<Certificado> getCertificados() {
        return certificados;
    }

    public void setCertificados(List<Certificado> certificados) {
        this.certificados = certificados;
    }

    @Override
    public String toString() {
        return
                "id= " + id + '\'' +
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
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

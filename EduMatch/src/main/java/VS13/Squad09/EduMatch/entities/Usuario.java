package VS13.Squad9.EduMatch.entities;

import VS13.Squad9.EduMatch.entities.Certificado;
import VS13.Squad9.EduMatch.entities.Contato;
import VS13.Squad9.EduMatch.entities.Endereco;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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

    public Usuario() {
        this.pontuacao = 0;
        this.id_empresa = null;
        this.id_escola = null;
    }

    public Usuario(String email, String senha, String nome, String sobrenome, String CPF,
                    Integer idade) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.CPF = CPF;
        this.idade = idade;
        this.pontuacao = 0;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id){
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

    public void setPontuacao(Integer pontuacao){
        this.pontuacao += pontuacao;
    }

    public Integer getId_escola() {
        return id_escola;
    }

    public void setId_escola(Integer id_escola) {
        this.id_escola = id_escola;
    }

    public Integer getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Integer id_empresa) {
        this.id_empresa = id_empresa;
    }

    public List<Certificado> getCertificados() {
        return certificados;
    }

    public void setCertificados(List<Certificado> certificados) {
        this.certificados = certificados;
    }

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

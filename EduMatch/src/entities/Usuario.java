package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Usuario {

    private Integer id;
    private String nome;
    private String sobrenome;
    private String CPF;
    private Integer idade;
    private Integer pontuacao;
    private Integer empresa;
    private List<Endereco> enderecos = new ArrayList<>();
    private List<Contato> contatos = new ArrayList<>();
    private List<Certificado> certificados = new ArrayList<>();
    private Integer escola;

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

    public Integer getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola.getId();
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void addEnderecos(Endereco endereco) {
        this.enderecos.add(endereco);
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void addContatos(Contato contato) {
        this.contatos.add(contato);
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

    public Integer getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa.getId();
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
                Escola: %s
                Pontuação: %d""", nome, sobrenome, CPF, idade, escola, pontuacao);
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

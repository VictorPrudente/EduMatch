package entities;

import java.util.ArrayList;

public class Empresa {
    private Usuario usuario;
    private String nome;
    private String setor;
    private String cnpj;
    private String areaDeAtuacao;
    private Endereco endereco;
    private int Id;

    public Empresa (){}
    public Empresa(String nome, String setor, String cnpj, String areaDeAtuacao,
                   int Id){
        this.nome = nome;
        this.setor = setor;
        this.cnpj = cnpj;
        this.areaDeAtuacao = areaDeAtuacao;
        this.Id = Id;
    }

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public String getSetor() {return setor;}
    public void setSetor(String setor) {this.setor = setor;}
    public String getCnpj() {return cnpj;}
    public void setCnpj(String cnpj) {this.cnpj = cnpj;}
    public String getAreaDeAtuacao() {return areaDeAtuacao;}
    public void setAreaDeAtuacao(String areaDeAtuacao) {this.areaDeAtuacao = areaDeAtuacao;}
    public Endereco getEndereco (){return endereco;}
    public void setEndereco(Endereco endereco){this.endereco = endereco;}
    public Usuario getUsuario() {return usuario;}
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}
    public int getId() {return Id;}
    public void setId(int id) {this.Id = id;}

    //METHODS
    public void apoiarUmAluno(Usuario usuario){
        System.out.println("Olá, " + usuario + "! Estamos vendo o progresso do seu resultado! " +
                "Continue dessa forma e, futuramente, poderá ter uma vaga" +
                "garantida na nossa empresa!");
    }
    public void cadastrarUmAluno(Usuario usuario){
        System.out.println("Usuário " + usuario + "cadastrado na empresa" + this.nome
                + "com sucesso!");
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "usuario=" + usuario +
                ", nome='" + nome + '\'' +
                ", setor='" + setor + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", areaDeAtuacao='" + areaDeAtuacao + '\'' +
                ", endereco=" + endereco +
                ", Id=" + Id +
                '}';
    }
}

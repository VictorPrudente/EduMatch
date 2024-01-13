package entities;

import java.util.ArrayList;

public class Empresa {
    private int Id;
    private String nome;
    private String cnpj;
    private String setor;
    private String areaDeAtuacao;
    private int tipo;
    private Usuario usuario;
    private Endereco endereco;

    public Empresa (){}
    public Empresa(int Id, String nome, String cnpj, String setor, String areaDeAtuacao, int tipo){
        this.Id = Id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.setor = setor;
        this.areaDeAtuacao = areaDeAtuacao;
        this.tipo = tipo;
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
    public void setTipo(int Tipo){this.tipo = tipo;}
    public int getTipo(){return tipo;}


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
        return String.format("""
                ID: %d
                Nome: %s
                Setor: %s
                Area de Atuação: %s
                Endereço: %s
                """, Id, nome, setor, areaDeAtuacao, endereco);
    }
}

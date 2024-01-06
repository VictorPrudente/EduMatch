package entities;

import enums.TipoEscola;

public class Escola {

    private int Id;
    private String nome;
    private TipoEscola tipo;
    private String cnpj;

    public Escola(String nome, TipoEscola tipo, String cnpj) {
        this.nome = nome;
        this.tipo = tipo;
        this.cnpj = cnpj;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo.name();
    }

    public void setTipo(TipoEscola tipo) {
        this.tipo = tipo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "Escola{" +
                "Id=" + Id +
                ", nome='" + nome + '\'' +
                ", tipo=" + tipo +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }

}

package VS13.Squad9.EduMatch.entities;

import VS13.Squad9.EduMatch.entities.enums.TipoEscola;

public class Escola {

    private int Id;
    private String nome;
    private TipoEscola tipo;
    private String cnpj;

    public Escola (){}

    public Escola(String nome, TipoEscola tipo, String cnpj) {
        this.nome = nome;
        this.tipo = tipo;
        this.cnpj = cnpj;
    }

    public Escola(String nome, TipoEscola tipo){
        this.nome = nome;
        this.tipo = tipo;
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

    public TipoEscola getTipo() {
        return tipo;
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
        return String.format("""
                Id: %d
                Nome: %s
                Tipo: %s""", Id,  nome, tipo);

    }
}
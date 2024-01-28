package VS13.Squad09.EduMatch.entities;

import VS13.Squad09.EduMatch.entities.enums.TipoEmpresa;

public class Escola {

    private int Id;
    private String nome;
    private TipoEmpresa tipo;
    private String cnpj;

    public Escola (){}

    public Escola(String nome, TipoEmpresa tipo, String cnpj) {
        this.nome = nome;
        this.tipo = tipo;
        this.cnpj = cnpj;
    }

    public Escola(String nome, TipoEmpresa tipo){
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

    public TipoEmpresa getTipo() {
        return tipo;
    }

    public void setTipo(TipoEmpresa tipo) {
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

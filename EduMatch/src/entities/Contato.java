package entities;
import entities.enums.TipoDeContato;

public class Contato {
    public static int ULTIMO_ID=1;
    private String descricao;
    private String telefone;
    private TipoDeContato tipo;
    private int Id;

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public TipoDeContato getTipo() {
        return tipo;
    }
    public void setTipo(TipoDeContato tipo) {
        this.tipo=tipo;

    }
    public int getId() {
        return Id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return Id == contato.Id;
    }
    public Contato(String descricao, String telefone, TipoDeContato tipo) {
        this.descricao = descricao;
        this.telefone = telefone;
        this.tipo = tipo;
        Id = ULTIMO_ID;
        Contato.ULTIMO_ID++;
    }

    public Contato (int id){
        Id=id;
    }
    public void imprimir (){
        System.out.println("Descrição: " + getDescricao());
        System.out.println("Telefone: " + getTelefone());
        System.out.println("Tipo: " + getTipo());
    }
}

package entities;
import java.util.Objects;
import java.util.Scanner;
public class Contato {
    public static int ULTIMO_ID=1;
    private String descricao;
    private String telefone;
    private Tipo tipo;
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
    public Tipo getTipo() {
        return tipo;
    }
    public void setTipo(int tipo) {
        if (tipo == 1){
            this.tipo=Tipo.RESIDENCIAL;
        }else{
            this.tipo=Tipo.COMERCIAL;
        }
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
    public Contato(String descricao, String telefone, int tipo) {
        this.descricao = descricao;
        this.telefone = telefone;

        if (tipo == 1){
            this.tipo=Tipo.RESIDENCIAL;
        }else{
            this.tipo=Tipo.COMERCIAL;
        }
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
enum Tipo {
    RESIDENCIAL,
    COMERCIAL
}
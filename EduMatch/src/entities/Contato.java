package entities;
import entities.enums.TipoDeContato;

public class Contato {
    private String descricao;
    private String telefone;
    private TipoDeContato tipo;

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

    public Contato(String descricao, String telefone, TipoDeContato tipo) {
        this.descricao = descricao;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    @Override
    public String toString(){
        return String.format("""
                -=-=-=-=-=-=-=-=-=-=-=
                Número: %s
                Descrição: %s
                Tipo: %s""",  telefone, descricao, tipo.name());
    }
}

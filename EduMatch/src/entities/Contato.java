package entities;
import entities.enums.TipoDeContato;

public class Contato {
    private Integer id;
    private String descricao;
    private String telefone;
    private TipoDeContato tipo;
    private Integer id_usuario;
    private Integer id_empresa;
    private Integer id_escola;

    public Contato(){}

    public Integer getId_empresa() {
        return id_empresa;
    }
    public void setId_empresa(Integer id_empresa) {
        this.id_empresa = id_empresa;
    }
    public Integer getId_escola() {
        return id_escola;
    }
    public void setId_escola(Integer id_escola) {
        this.id_escola = id_escola;
    }
    public Integer getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id_) {
        this.id = id;
    }
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
                Id: %d
                Número: %s
                Descrição: %s
                Tipo: %s""",  id, telefone, descricao, tipo.name());
    }
}

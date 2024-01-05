package entities;
public class Empresa {
    private Usuario usuario;
    private String nome;
    private String setor;
    private String cnpj;
    private String areaDeAtuacao;
    private Endereco endereco;

    public Empresa (){}
    public Empresa(String nome, String setor, String cnpj, String areaDeAtuacao
            , Endereco endereco){
        this.nome = nome;
        this.setor = setor;
        this.cnpj = cnpj;
        this.areaDeAtuacao = areaDeAtuacao;
        this.endereco = endereco;
    }

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public String getSetor() {return setor;}
    public void setSetor(String setor) {this.setor = setor;}
    public String getCnpj() {return cnpj;}
    public void setCnpj(String cnpj) {this.cnpj = cnpj;}
    public String getAreaDeAtuacao() {return areaDeAtuacao;}
    public void setAreaDeAtuacao(String areaDeAtuacao) {this.areaDeAtuacao = areaDeAtuacao;}
    public Endereco getEndereco (Endereco endereco){return endereco;}
    public void setEndereco(){this.endereco = endereco;}
    public Usuario getUsuario() {return usuario;}
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}

    public void apoiarUmAluno(Usuario usuario){
        System.out.println("Olá, " + usuario + "! Estamos vendo o progresso do seu resultado! " +
                "Continue dessa forma e, futuramente, poderá ter uma vaga" +
                "garantida na nossa empresa!");
    }
    public void cadastrarUmAluno(Usuario usuario){
        System.out.println("Usuário " + usuario + "cadastrado na empresa" + this.nome
        + "com sucesso!");
    }
}

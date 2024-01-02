package entities;

public class Empresa {
    Usuario usuario;
    public String nome;
    public String setor;
    public String cnpj;
    public String areaDeAtuacao;

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

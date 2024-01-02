package entities;

public class Endereco {
    String logradouro;
    Integer numero;
    String complemento;
    String cep;
    String cidade;
    String estado;
    String pais;

    public void imprimirEndereco(){
        System.out.println("Endereço: " + this.logradouro + "," + this.numero);
        System.out.println("Complemento: " + this.complemento);
        System.out.println("CEP: " + this.cep);
        System.out.println("Cidade" + this.cidade);
        System.out.println("Estado: " + this.estado);
        System.out.println("País: " + this.pais);
    }
}

package entities;

import java.util.Scanner;

public class Contato {
    private String descricao;
    private String telefone;
    private int tipo;

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

    public String getTipo() {

        if (tipo==1) {
            return "residencial";
        } else {
            return "comercial";
        }
    }
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void imprimir (){
        System.out.println("Descrição: " + getDescricao());
        System.out.println("Telefone: " + getTelefone());
        System.out.println("Tipo: " + getTipo());
    }
    public void criar (){
        Scanner teclado = new Scanner(System.in);
        System.out.println("Digite endereço: ");
        setDescricao(teclado.next());

        System.out.println("Digite telefone: ");
        setTelefone(teclado.next());

        System.out.println("Tipo: (1 Residencial / 2 Comercial) ");
        setTipo(teclado.nextInt());
    }
}

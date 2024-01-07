package utils;

import entities.Contato;
import entities.Endereco;
import entities.Escola;
import entities.Usuario;
import entities.enums.TipoDeContato;
import entities.enums.TipoEscola;

import java.util.Scanner;

public class Cadastro {

    public Usuario cadastrarUsuario(Scanner sc){

        System.out.print("Por favor, digite seu nome: ");
        String nome = sc.nextLine();

        System.out.print("Por favor, digite Sobrenome: ");
        String sobrenome = sc.nextLine();

        System.out.print("Agora, digite seu CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Por favor, Digite sua idade: ");
        int idade = sc.nextInt();
        sc.nextLine();

        return new Usuario(nome, sobrenome, cpf, idade, 0);
    }

    public Contato cadastrarContato(Scanner sc){

        System.out.print("\nNúmero para contato: ");
        String telefone = sc.nextLine();

        System.out.print("Descrição: ");
        String descricao = sc.nextLine();

        System.out.print("Tipo de contato\n" +
                "[1] Celular\n" +
                "[2] Residencial\n" +
                "[3] Comercial\n");
        System.out.print("Opção: ");
        int opcao = sc.nextInt();
        sc.nextLine();

        return new Contato(descricao, telefone, TipoDeContato.valueOf(opcao-1));
    }

    public Endereco cadastrarEndereco(Scanner sc){

        System.out.print("Rua: ");
        String rua = sc.nextLine();

        System.out.print("Número: ");
        int numero = sc.nextInt();
        sc.nextLine();

        System.out.print("Complemento: ");
        String compl = sc.nextLine();

        System.out.print("CEP: ");
        String cep = sc.nextLine();

        System.out.print("Cidade: ");
        String cidade = sc.nextLine();

        System.out.print("Estado: ");
        String estado = sc.nextLine();

        System.out.print("País: ");
        String pais = sc.nextLine();

        return new Endereco(rua, numero, compl, cep,cidade, estado, pais);
    }

    public Escola cadastrarEscola(Scanner sc){

        System.out.print("Digite o nome da escola: ");
        String nome = sc.nextLine();

        System.out.print("Tipo de escola\n" +
                "[1] Privada\n" +
                "[2] Publica\n" +
                "[3] Técnica\n");
        System.out.print("Opção: ");
        int tipo = sc.nextInt();

        return new Escola(nome, TipoEscola.valueOf(tipo));
    }
}

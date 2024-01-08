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
    while (true) {

        try {
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
            } catch (RuntimeException e) {
            System.out.println("Erro ao ler os dados. Por favor, tente novamente.\n");
            sc.nextLine();
            }

        }
    }

    public Contato cadastrarContato(Scanner sc){
    while (true){
        try {
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
        } catch (RuntimeException e){
            System.out.println("Erro ao ler os dados. Por favor, tente novamente.");
            sc.nextLine();
        }
        }
    }

    public Endereco cadastrarEndereco(Scanner sc){
        while (true){
            try{
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
        } catch (RuntimeException e){
            System.out.println("Erro ao ler os dados. Por favor, tente novamente.");
            sc.nextLine();
            }
        }
    }

    public Escola cadastrarEscola(Scanner sc){
    while (true) {
        try {
            System.out.print("Digite o nome da escola: ");
            String nome = sc.nextLine();

            System.out.print("Tipo de escola\n" +
                    "[1] Privada\n" +
                    "[2] Publica\n" +
                    "[3] Técnica\n");
            System.out.print("Opção: ");
            int tipo = sc.nextInt();
            sc.nextLine();

            return new Escola(nome, TipoEscola.valueOf(tipo - 1));
        } catch (RuntimeException e){
            System.out.println("Erro ao ler os dados. Por favor, tente novamente.");
            sc.nextLine();
        }
    }
    }
}

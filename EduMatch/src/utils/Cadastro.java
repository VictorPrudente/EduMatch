package utils;

import entities.*;
import entities.enums.Games;
import entities.enums.TipoDeContato;
import entities.enums.TipoEscola;
import exceptions.BancoDeDadosException;
import repository.UsuarioRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Cadastro {

    UsuarioRepository usuarioRepository = new UsuarioRepository();


    public String hashPassword(String senha){
        try {
            MessageDigest cript = MessageDigest.getInstance("SHA-256");
            byte[] passwordBytes = senha.getBytes(StandardCharsets.UTF_8);
            byte[] hashedBytes = cript.digest(passwordBytes);

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes){
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }

    public Usuario Login(Scanner sc) {
        Usuario usuario = null;
        boolean noUser = true;
        while (noUser){
            System.out.print("\nDigite seu email de login: ");
            String email = sc.nextLine();
            System.out.print("\nDigite sua senha: ");
            String senha = sc.nextLine();
            try{
                usuario = usuarioRepository.listarPorEmail(email);

                if (usuario != null && hashPassword(senha).equals(usuario.getSenha())){
                    noUser = false;
                    System.out.println("\nOlá " + usuario.getNome() + " que bom te ver de volta! Vamos aos estudos?!");
                }
                else {
                    System.out.println("Email ou senha inválida. Por favor, tente novamente.\n");
                }
            } catch (BancoDeDadosException e){
                System.out.println("Erro ao acessar o banco de dados.");
                System.out.println("Erro: " + e.getMessage());
                return null;
            }
        }
        return usuario;
    }

    public Usuario cadastrarUsuario(Scanner sc){
    while (true) {
        String email = "";
        String senha = "";
        String nome = "";
        String sobrenome = "";
        String cpf = "";


        try{
            while (email.isBlank()){
                System.out.print("Por favor, digite seu email para login: ");
                email = sc.nextLine();
            }

            while (senha.isBlank()){
                System.out.print("Por favor, digite sua senha: ");
                senha = sc.nextLine();
                senha = hashPassword(senha);
            }

            while (nome.isBlank() || nome.matches(".*\\d.*")) {
                System.out.print("Por favor, digite seu nome: ");
                nome = sc.nextLine();
            }
            while (sobrenome.isBlank() || sobrenome.matches(".*\\d.*")) {
            System.out.print("Por favor, digite Sobrenome: ");
                sobrenome = sc.nextLine();
            }
            while (cpf.isBlank() || !cpf.matches("\\b(?:\\d{3}\\.?){3}\\d{2}\\b")) {
            System.out.print("Agora, digite seu CPF: ");
                cpf = sc.nextLine();
            }
            System.out.print("Por favor, Digite sua idade: ");
            int idade = sc.nextInt();
            sc.nextLine();
            return new Usuario(email, senha, nome, sobrenome, cpf, idade);
            } catch (RuntimeException e) {
            System.out.println("\n\u001B[31mErro ao ler os dados. Por favor, tente novamente.\u001B[0m\n");
            sc.nextLine();
            }

        }
    }

    public Contato cadastrarContato(Scanner sc){
    while (true){
        String telefone = "";
        String descricao = "";
        try {
            while (telefone.isBlank() || !telefone.matches("^[0-9]+$")) {
                System.out.print("Número para contato: ");
                telefone = sc.nextLine();
            }
            while (descricao.isBlank()) {
                System.out.print("Descrição: ");
                descricao = sc.nextLine();
            }
            System.out.print("Tipo de contato\n" +
                    "[1] Celular\n" +
                    "[2] Residencial\n" +
                    "[3] Comercial\n");
            System.out.print("Opção: ");
            int opcao = sc.nextInt();
            sc.nextLine();
        return new Contato(descricao, telefone, TipoDeContato.valueOf(opcao-1));
        } catch (RuntimeException e){
            System.out.println("\n\u001B[31mErro ao ler os dados. Por favor, tente novamente.\u001B[0m");
            sc.nextLine();
        }
        }
    }

    public Endereco cadastrarEndereco(Scanner sc){
        while (true){
            String rua = "";
            String complemento = "";
            String CEP = "";
            String cidade = "";
            String estado = "";
            String pais = "";
            try{
                while (rua.isBlank()) {
                    System.out.print("Rua: ");
                    rua = sc.nextLine();
                }
                System.out.print("Número: ");
                int numero = sc.nextInt();
                sc.nextLine();
                while (complemento.isBlank()) {
                    System.out.print("Complemento: ");
                    complemento = sc.nextLine();
                }
                while (CEP.isBlank() || CEP.matches(".*[a-zA-Z].*")) {
                    System.out.print("CEP: ");
                    CEP = sc.nextLine();
                }
                while (cidade.isBlank() || cidade.matches(".*\\d.*")) {
                    System.out.print("Cidade: ");
                    cidade = sc.nextLine();
                }
                while (estado.isBlank() || estado.matches(".*\\d.*")) {
                    System.out.print("Estado: ");
                    estado = sc.nextLine();
                }
                while (pais.isBlank() || pais.matches(".*\\d.*")) {
                    System.out.print("País: ");
                    pais = sc.nextLine();
                }
            return new Endereco(rua, numero, complemento, CEP, cidade, estado, pais);
        } catch (RuntimeException e){
            System.out.println("\n\u001B[31mErro ao ler os dados. Por favor, tente novamente.\u001B[0m");
            sc.nextLine();
            }
        }
    }

    public Escola cadastrarEscola(Scanner sc){
    while (true) {
        String nome = "";
        try {
            while (nome.isBlank()) {
                System.out.print("Digite o nome da escola: ");
                nome = sc.nextLine();
            }
            System.out.print("Tipo de escola\n" +
                    "[1] Privada\n" +
                    "[2] Publica\n" +
                    "[3] Técnica\n");
            System.out.print("Opção: ");
            int tipo = sc.nextInt();
            sc.nextLine();

            return new Escola(nome, TipoEscola.valueOf(tipo - 1));
        } catch (RuntimeException e){
            System.out.println("\n\u001B[31mErro ao ler os dados. Por favor, tente novamente.\u001B[0m");
            sc.nextLine();
        }
    }
    }
}

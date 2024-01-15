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

    public Usuario Login(String email, String senha) throws BancoDeDadosException {
        try {
            Usuario usuario = usuarioRepository.listarPorEmail(email);
            String senhaHasheada = hashPassword(senha);
            if (senhaHasheada.equals(usuario.getSenha())) {
                //adicionar contato ao usuario
                //adicionar endereço ao usuario
                return usuario;
            } else {
                System.out.println("Senha inválida.");
                return null;
            }
        } catch (BancoDeDadosException e){
            System.out.println("Usuario não encontrado.");
            return null;
        }
    }

    public Certificado validarCertificado(int acertos, int totalQuestoes, Usuario usuario, int i) {
        if ((double) ((acertos * 100) / totalQuestoes) >= 66) {
            return new Certificado(Games.valueOf(i), LocalDateTime.now(), usuario);
        }
        return null;
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

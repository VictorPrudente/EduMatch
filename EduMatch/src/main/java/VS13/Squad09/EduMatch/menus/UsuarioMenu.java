package VS13.Squad9.EduMatch.menu;

import entities.*;
import services.ContatoService;
import services.EnderecoService;
import services.UsuarioService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class UsuarioMenu {

    UsuarioService usuarioService = new UsuarioService();
    EnderecoService enderecoService = new EnderecoService();
    ContatoService contatoService = new ContatoService();
    MenuTextos menuTextos = new MenuTextos();

    public Usuario Login(Scanner sc, String input, Usuario usuario) {
        menuTextos.menuLogin();
        int opcao = menuTextos.entradaUsuario(sc, input);
        switch (opcao) {
            case 1: {
                return validarEntradas(sc, usuario);
            }
            case 2:
                usuario = cadastrarUsuario(sc);
                if(usuarioService.salvar(usuario)){
                    return usuario;
                }
                break;
            case 3:
                System.out.println("\nFinalizando a aplicação. Até logo!");
                System.exit(0);
        }
        Endereco endereco = enderecoService.listarPorDono(usuario.getId());
        if (endereco != null) {
            usuario.setEndereco(endereco);
        }
        Contato contato = contatoService.listarPorDono(usuario.getId());
        if (contato != null) {
            usuario.setContato(contato);
        }
        return null;
    }

    private String hashPassword(String senha){
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

    private Usuario validarEntradas(Scanner sc, Usuario usuario) {
        boolean noUser = true;
        while (noUser) {
            System.out.print("\nDigite seu email de login: ");
            String email = sc.nextLine();
            System.out.print("Digite sua senha: ");
            String senha = sc.nextLine();
            usuario = usuarioService.listarPorEmail(email);

            if (usuario != null && hashPassword(senha).equals(usuario.getSenha())) {
                noUser = false;
                System.out.println("\nOlá " + usuario.getNome() + " que bom te ver de volta! Vamos aos estudos?!");
            } else {
                System.out.println("Email ou senha inválida. Por favor, tente novamente.\n");
            }
        }
        return usuario;
    }

    private Usuario cadastrarUsuario(Scanner sc) {
        String email;
        String senha;
        String nome;
        String sobrenome;
        String cpf;
        do {
            System.out.print("Por favor, digite seu email para login: ");
            email = sc.nextLine();
        } while (email.isBlank());

        do {
            System.out.print("Por favor, digite sua senha: ");
            senha = sc.nextLine();
            //LEVAR O MÉTODO DE HASH PARA A CAMADA DE SERVICE
            senha = hashPassword(senha);
        } while (senha.isBlank());

        do {
            System.out.print("Por favor, digite seu nome: ");
            nome = sc.nextLine();
        } while (nome.isBlank() || nome.matches(".*\\d.*"));

        do {
            System.out.print("Por favor, digite Sobrenome: ");
            sobrenome = sc.nextLine();
        } while (sobrenome.isBlank() || sobrenome.matches(".*\\d.*"));

        do {
            System.out.print("Agora, digite seu CPF: ");
            cpf = sc.nextLine();
        } while (cpf.isBlank() || !cpf.matches("\\b(?:\\d{3}\\.?){3}\\d{2}\\b"));

        System.out.print("Por favor, Digite sua idade: ");
        int idade = menuTextos.entradaUsuario(sc, "");

        return new Usuario(email, senha, nome, sobrenome, cpf, idade);
    }
}

package utils;

import entities.Contato;
import entities.Usuario;
import entities.enums.TipoDeContato;
import services.ContatoService;

import java.util.Scanner;

public class ContatoCRUD {

    Menu menu = new Menu();
    private final ContatoService contatoService = new ContatoService();
    private final String erro = "\nVocê não tem nenhum endereço cadastrado.";
    private final String erroCadastro = "\nVocê já possui um endereço cadastrado.";


    public void contatoOpcoes(Scanner sc, int opcao, Usuario usuario) {
        switch (opcao) {
            case 1: {
                listar(usuario);
                return;
            }
            case 2: {
                salvar(sc, usuario);
                return;
            }
            case 3: {
                atualizar(sc, usuario);
                return;
            }
            case 4: {
                deletar(sc, usuario);
                return;
            }
            case 5: {
                return;
            }
            default: {
                System.out.println("\nOpção inválida. Retornando ao menu.");
                break;
            }
        }
    }

    private void listar(Usuario usuario) {
        if (temContato(usuario)) {
            System.out.println("CONTATO CADASTRADO");
            System.out.println(usuario.getContato());
        } else {
            System.out.println(erro);
        }
    }

    private void salvar(Scanner sc, Usuario usuario) {
        if (!temContato(usuario)) {

            System.out.println("\nCADASTRO DE UM NOVO CONTATO");
            Contato novoContato = cadastrar(sc);
            novoContato.setId_usuario(usuario.getId());

            if(contatoService.salvar(novoContato)) {
                usuario.setContato(novoContato);
                System.out.println("Contato cadastrado com sucesso!\n");
            }
        } else
            System.out.println(erroCadastro);
    }

    private void atualizar(Scanner sc, Usuario usuario) {
        if (temContato(usuario)) {
            System.out.println("ATUALIZAR CONTATO");
            System.out.println(usuario.getContato());
            System.out.print("Insira o id do seu contato para atualiza-lo ou 0 para cancelar a operação: ");
            int id = menu.entradaUsuario(sc);
            if (id == 0) {
                return;
            }
            Contato contatoAtualizado = cadastrar(sc);
            contatoAtualizado.setId_usuario(usuario.getContato().getId());
            if (contatoService.atualizar(id, contatoAtualizado)) {
                usuario.setContato(contatoAtualizado);
            }
        } else
            System.out.println(erro);
    }

    private void deletar(Scanner sc, Usuario usuario){
        if (temContato(usuario)) {
            System.out.println(usuario.getContato());
            System.out.println("Escolha o ID do contato a ser deletado ou 0 para cancelar a operação: ");
            int opcao = menu.entradaUsuario(sc);
            if (opcao == 0){
                return;
            }
            if (contatoService.deletar(opcao)){
                usuario.setContato(null);
            }
        } else {
            System.out.println(erro);
        }
    }

    private boolean temContato(Usuario usuario) {
        return usuario.getContato() != null;
    }

    private Contato cadastrar(Scanner sc) {
        while (true) {

            String telefone;
            String descricao;

            try {
                do {
                    System.out.print("Número para contato: ");
                    telefone = sc.nextLine();
                } while (telefone.isBlank() || !telefone.matches("^[0-9]+$"));

                do {
                    System.out.print("Descrição: ");
                    descricao = sc.nextLine();
                } while (descricao.isBlank());

                int opcao;
                do {
                    System.out.print("""
                            Tipo de contato
                            [1] Celular
                            [2] Residencial
                            [3] Comercial
                            """);
                    opcao = menu.entradaUsuario(sc);
                } while (opcao < 1 || opcao > 3);

                return new Contato(descricao, telefone, TipoDeContato.valueOf(opcao - 1));

            } catch (RuntimeException e) {
                System.out.println("\n\u001B[31mErro ao ler os dados. Por favor, tente novamente.\u001B[0m");
                sc.nextLine();
            }
        }
    }
}

package utils;

import entities.Escola;
import entities.Usuario;
import entities.enums.TipoEscola;
import services.EscolaService;
import services.UsuarioService;

import java.util.Scanner;

public class EscolaController {

    EscolaService escolaService = new EscolaService();
    UsuarioService usuarioService = new UsuarioService();

    Menu menu = new Menu();

    public void escolaOpcoes(Scanner sc, int opcao, Usuario usuario) {
        switch (opcao) {
            case 1: {
                Escola escola = new Escola();
                escolaService.listarTodos();
                System.out.print("Escolha o ID de uma escola para se cadastrar (Digite o número 0 caso não tenha na lista): ");
                opcao = sc.nextInt();
                sc.nextLine();
                if (opcao == 0) {
                    escola = cadastrarEscola(sc);
                    escolaService.salvar(escola);
                } else {
                    escola = escolaService.listarPorId(opcao);
                }
                    usuario.setId_escola(escola.getId());
                    usuarioService.atualizar(usuario.getId(), usuario);
                    return;
                }
            case 2: {
                System.out.println("\n Retornando ao menu principal");
            }
        }
    }

    private Escola cadastrarEscola(Scanner sc) {
        String nome;
        int tipo;
        do {
            System.out.print("Digite o nome da escola: ");
            nome = sc.nextLine();
        } while (nome.isBlank());

        System.out.print("""
                Tipo de escola:
                "[1] Privada
                "[2] Publica
                "[3] Técnica""");
        tipo = menu.entradaUsuario(sc, "opcao: ");

        return new Escola(nome, TipoEscola.valueOf(tipo - 1));
    }
}

import entities.*;
import exceptions.InputException;
import services.*;
import utils.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        CertificadoService certificadoService = new CertificadoService();
        EscolaController escolaController = new EscolaController();
        EmpresaService empresaService = new EmpresaService();
        UsuarioService usuarioService = new UsuarioService();
        EnderecoController enderecoController = new EnderecoController();
        ContatoController contatoController = new ContatoController();
        RankingController rankingController = new RankingController();
        CertificadoController certificadoController = new CertificadoController();
        UsuarioController usuarioController = new UsuarioController();
        Jogo jogo = new Jogo();
        Menu menu = new Menu();
        Scanner sc = new Scanner(System.in);
        Usuario usuario = null;
        int opcao;
        boolean execucao = true;
        String error = "\n\u001B[31mOpção Inválida. Retornando ao menu principal.\u001B[0m\n";
        String input = "Digite sua opção: ";


        System.out.println("BEM VINDOS AO EDUMATCH");
        do {
            usuario = usuarioController.Login(sc, input, usuario);
        } while (usuario == null);

        while (execucao) {
            menu.menuPrincipal();
            opcao = menu.entradaUsuario(sc, input);

            switch (opcao) {
                //NOVO JOGO
                case 1: {
                    System.out.println();
                    menu.menuNovoJogo();
                    opcao = menu.entradaUsuario(sc, input);
                    Certificado certificado = jogo.jogar(sc, opcao, usuario);
                    if (certificado != null) {
                        certificadoService.salvar(certificado);
                    } else
                        System.out.println("Você não conseguiu seu certificado desta vez. Acerte mais questões para conseguir!\n");

                    usuarioService.atualizar(usuario.getId(), usuario);
                }
                break;
                //RANKING
                case 2: {
                    menu.menuRanking();
                    opcao = menu.entradaUsuario(sc, input);
                    rankingController.rankingOpcoes(opcao, usuario);
                    break;
                }
                //OPÇÕES
                case 3: {
                    menu.menuOpcoes();
                    opcao = menu.entradaUsuario(sc, input);

                    switch (opcao) {
                        //ENDEREÇO
                        case 1: {
                            menu.menuEndereco();
                            opcao = menu.entradaUsuario(sc, input);
                            enderecoController.enderecoOpcoes(sc, opcao, usuario);
                        }
                        break;
                        //CONTATO
                        case 2: {
                            menu.menuContato();
                            opcao = menu.entradaUsuario(sc, input);
                            contatoController.contatoOpcoes(sc, opcao, usuario);
                            break;
                        }
                        //ESCOLA
                        case 3: {
                            menu.menuEscola();
                            opcao = menu.entradaUsuario(sc, input);
                            escolaController.escolaOpcoes(sc, opcao, usuario);
                            break;
                        }
                        //CERTIFICADO
                        case 4: {
                            menu.menuCertificado();
                            opcao = menu.entradaUsuario(sc, input);
                            certificadoController.certificadoOpcao(opcao, usuario);
                            break;
                        }
                        //DADOS JOGADOR
                        case 5: {
                            System.out.println("\n" + usuario.imprimirDados() + "\n");
                            continue;
                        }
                        //EMPRESAS
                        case 6: {
                            empresaService.listarTodos();
                            break;
                        }
                        case 7: {
                            System.out.println("\nRetornar ao Menu Principal\n");
                            break;
                        }
                        default: {
                            System.out.println(error);
                        }
                    }
                    break;
                }
                //FINALIZAR
                case 4: {
                    System.out.println("\nFinalizando a aplicação. Até logo!");
                    System.exit(0);
                }
                default:
                    System.out.println("\n\u001B[31mOpção Inválida. Tente novamente.\u001B[0m\n");
            }
        }
    }
}

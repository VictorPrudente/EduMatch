import entities.*;
import entities.enums.Dificuldades;
import entities.enums.Games;
import services.*;
import utils.Cadastro;
import utils.Menu;

import java.time.LocalDateTime;
import java.util.Random;
import java.awt.*;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        EscolaService escolaService = new EscolaService();
        UsuarioService usuarioService = new UsuarioService();
        SoftSkillService softSkillService = new SoftSkillService();
        EmpresaService empresaService = new EmpresaService();
        PortuguesService portuguesService = new PortuguesService();
        MatematicaService matematicaService = new MatematicaService();
        Cadastro cadastro = new Cadastro();
        Random random = new Random();
        Menu menu = new Menu();
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        boolean execucao = true;
        String opcaoQuestao = "";


        System.out.println("BEM VINDOS AO EDUMATCH");

        Usuario usuario = cadastro.cadastrarUsuario(sc);
        usuarioService.salvar(usuario);

        System.out.println();

        while (execucao) {
            menu.menuPrincipal();
            System.out.print("Digite sua opção: ");
            try {
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    //NOVO JOGO
                    case 1: {
                        System.out.println();
                        menu.menuNovoJogo();
                        System.out.print("Digite sua opção: ");
                        opcao = sc.nextInt();
                        sc.nextLine();
                        switch (opcao) {
                            case 1: {
                                int j = 0;
                                for (int i = 0; i < 3; i++) {
                                    Portugues questao = portuguesService.listarPelaDificuldade(Dificuldades.valueOf(i)).get(random.nextInt(0, 2));
                                    System.out.println(questao);

                                    while (!opcaoQuestao.matches("[A-Ea-e]")) {
                                        System.out.print("Opção: ");
                                        opcaoQuestao = sc.nextLine().toUpperCase().trim();
                                    }
                                        j += menu.validarQuestao(opcaoQuestao, questao, usuario);
                                        opcaoQuestao = "";
                                } if (j >= 2){
                                    Certificado certificado =new Certificado(Games.SOFT_SKILLS, LocalDateTime.now(), usuario);
                                    usuario.getCertificados().add(certificado);
                                    System.out.println("Parabéns pelo seu certificado!");
                                }
                                break;
                            }
                            case 2: {
                                int j = 0;
                                for (int i = 0; i < 3; i++) {
                                    Matematica questao = matematicaService.listarPelaDificuldade(Dificuldades.valueOf(i)).get(random.nextInt(0, 2));
                                    System.out.println(questao);

                                    while (!opcaoQuestao.matches("[A-Ea-e]")) {
                                        System.out.print("Opção: ");
                                        opcaoQuestao = sc.nextLine().toUpperCase().trim();
                                    }
                                         j += menu.validarQuestao(opcaoQuestao, questao, usuario);
                                        opcaoQuestao = "";
                                }
                                if (j >= 2){
                                    Certificado certificado =new Certificado(Games.SOFT_SKILLS, LocalDateTime.now(), usuario);
                                    usuario.getCertificados().add(certificado);
                                    System.out.println("Parabéns pelo seu certificado!");
                                }
                                break;
                            }
                            case 3: {
                                System.out.println();
                                    int j = 0;
                                for (int i = 0; i < 3; i++) {
                                    SoftSkill questao = softSkillService.listarPelaDificuldade(Dificuldades.valueOf(i)).get(i);
                                    System.out.println(questao);

                                    while (!opcaoQuestao.matches("[A-Ea-e]")) {
                                        System.out.print("Opção: ");
                                        opcaoQuestao = sc.nextLine().toUpperCase().trim();
                                    }
                                       j += menu.validarQuestao(opcaoQuestao, questao, usuario);
                                        opcaoQuestao = "";
                                }
                                if (j >= 2){
                                    Certificado certificado =new Certificado(Games.SOFT_SKILLS, LocalDateTime.now(), usuario);
                                    usuario.getCertificados().add(certificado);
                                    System.out.println("Parabéns pelo seu certificado!\n");
                                }
                                break;
                            } default:
                                System.out.println("\n\u001B[31mOpção Inválida. Retornando ao menu principal.\u001B[0m\n");
                                break;
                        }
                        break;
                    }
                    //RANKING
                    case 2: {
                        menu.menuRanking();
                        System.out.print("Opção: ");
                        opcao = sc.nextInt();
                        switch (opcao) {
                            case 1: {
                                System.out.println("\nSua pontuação é de: " + usuario.getPontuacao() + "\n");
                                continue;
                            }
                            case 2: {
                                usuarioService.rankearUsuarios();
                                System.out.println();
                                System.out.println();
                                continue;
                            }
                            case 3: {
                                System.out.println("\nRetornando ao Menu Principal");
                                continue;
                            }
                            default: {
                                System.out.println("\n\u001B[31mOpção Inválida. Retornando ao menu principal.\u001B[0m\n");
                            }
                        }
                    }
                    break;
                    //OPÇÕES
                    case 3: {
                        menu.menuOpcoes();
                        System.out.print("Opção: ");
                        opcao = sc.nextInt();
                        sc.nextLine();

                        switch (opcao) {
                            case 1: {
                                menu.menuEndereco();
                                System.out.print("Opção: ");
                                opcao = sc.nextInt();
                                sc.nextLine();
                                switch (opcao) {
                                    case 1: {
                                        if (!usuario.getEnderecos().isEmpty()) {
                                            System.out.println("ENDEREÇOS CADASTRADOS");
                                            for (Endereco endereco : usuario.getEnderecos()) {
                                                System.out.println("\n" + endereco + "\n");
                                            }
                                        } else {
                                            System.out.println("\nNenhum endereço cadastrado.\n");
                                        }
                                        continue;
                                    }
                                    case 2: {
                                        System.out.println();

                                        System.out.println("CADASTRO DE UM NOVO ENDEREÇO");
                                        Endereco endereco = cadastro.cadastrarEndereco(sc);
                                        usuario.addEnderecos(endereco);
                                        System.out.println("Endereço cadastrado com sucesso!\n");

                                        continue;
                                    }
                                    case 3: {
                                        //ATUALIZAR UM ENDEREÇO
                                        int i = 0;
                                        if (!usuario.getEnderecos().isEmpty()) {
                                            System.out.println("ATUALIZAR UM ENDEREÇO");
                                            for (Endereco endereco : usuario.getEnderecos()) {
                                                System.out.printf("[%d]", ++i);
                                                System.out.println(endereco + "\n");
                                            }
                                            System.out.print("Escolha um endereço pelo seu ID: ");
                                            int id = sc.nextInt();
                                            sc.nextLine();
                                            Endereco enderecoAtualizado = cadastro.cadastrarEndereco(sc);
                                            Endereco enderecoAntigo = usuario.getEnderecos().get(id - 1);
                                            usuario.addEnderecos(enderecoAtualizado);
                                            usuario.getEnderecos().remove(enderecoAntigo);
                                            System.out.println("Endereço atualizado com sucesso!\n");
                                        } else {
                                            System.out.println("Você não tem nenhum endereço cadastrado.\n");
                                        }
                                        break;
                                    }
                                    case 4: {
                                        //DELETAR UM ENDEREÇO
                                        int i = 0;
                                        if (!usuario.getEnderecos().isEmpty()) {
                                            System.out.println("DELETAR UM ENDEREÇO");
                                            for (Endereco endereco : usuario.getEnderecos()) {
                                                System.out.printf("[%d] ", ++i);
                                                System.out.println(endereco + "\n");
                                            }
                                            System.out.print("Escolha um endereço pelo seu ID: ");
                                            int id = sc.nextInt();
                                            Endereco endereco = usuario.getEnderecos().get(id - 1);
                                            usuario.getEnderecos().remove(endereco);
                                            System.out.println("Endereço deletado com sucesso!\n");
                                        } else {
                                            System.out.println("Você não tem nenhum endereço cadastrado.\n");
                                        }
                                        break;
                                    }
                                    case 5: {
                                        continue;
                                    }
                                }
                                continue;
                            }
                            case 2: {
                                //Contatos
                                menu.menuContato();
                                System.out.print("Opção: ");
                                opcao = sc.nextInt();
                                sc.nextLine();

                                switch (opcao) {
                                    case 1: {
                                        if (!usuario.getContatos().isEmpty()) {
                                            System.out.println("CONTATOS CADASTRADOS");
                                            for (Contato contato : usuario.getContatos()) {
                                                System.out.println(contato + "\n");
                                            }
                                        } else {
                                            System.out.println("\nNenhum contato cadastrado.\n");
                                        }
                                        continue;
                                    }
                                    case 2: {
                                        Contato contato = cadastro.cadastrarContato(sc);
                                        usuario.addContatos(contato);
                                        System.out.println("Contato cadastrado com sucesso!\n");
                                        break;
                                    }
                                    case 3: {
                                        int i = 0;
                                        if (!usuario.getContatos().isEmpty()) {
                                            for (Contato contato : usuario.getContatos()) {
                                                System.out.printf("[%d] ", ++i);
                                                System.out.print(contato);
                                                System.out.println();
                                            }
                                            System.out.println("Escolha o ID do contato a ser atualizado: ");
                                            System.out.print("Opção: ");
                                            opcao = sc.nextInt();
                                            sc.nextLine();
                                            Contato contato = usuario.getContatos().get(opcao - 1);
                                            Contato contatoAtualizado = cadastro.cadastrarContato(sc);
                                            usuario.addContatos(contatoAtualizado);
                                            usuario.getContatos().remove(contato);
                                        } else
                                            System.out.println("Lista de contatos vazia.\n");
                                        break;
                                    }
                                    case 4: {
                                        int i = 0;
                                        if (!usuario.getContatos().isEmpty()) {
                                            for (Contato contato : usuario.getContatos()) {
                                                System.out.printf("[%d] ", ++i);
                                                System.out.println(contato);
                                            }
                                            System.out.println("Escolha o ID do contato a ser deletado: ");
                                            System.out.print("Opção: ");
                                            opcao = sc.nextInt();
                                            Contato contato = usuario.getContatos().get(opcao - 1);
                                            usuario.getContatos().remove(contato);
                                        } else {
                                            System.out.println("Lista de contatos vazia.\n");
                                        }
                                    }
                                    case 5: {
                                        continue;
                                    }
                                    default:
                                        System.out.println("\n\u001B[31mOpção Inválida. Retornando ao menu principal.\u001B[0m\n");
                                }
                                continue;
                            }
                            case 3: {
                                menu.menuEscola();
                                System.out.print("Opção: ");
                                opcao = sc.nextInt();
                                sc.nextLine();
                                switch (opcao) {
                                    case 1: {
                                        System.out.println();
                                        escolaService.listarTodos();
                                        continue;
                                    }
                                    case 2: {
                                        escolaService.listarTodos();
                                        System.out.print("Escolha o ID de uma escola para se cadastrar (Digite o número 0 caso não tenha na lista): ");
                                        opcao = sc.nextInt();
                                        sc.nextLine();
                                        if (opcao == 0) {
                                            Escola escola = cadastro.cadastrarEscola(sc);
                                            escolaService.salvar(escola);
                                            usuario.setEscola(escola);
                                        } else {
                                            Escola escola = escolaService.listarPorId(opcao);
                                            usuario.setEscola(escola);
                                        }
                                        continue;
                                    }
                                    case 3: {
                                    }
                                    default: {
                                        System.out.println("\n\u001B[31mOpção Inválida. Retornando ao menu principal.\u001B[0m\n");
                                        continue;
                                    }
                                }
                            }
                            case 4: {
                                menu.menuCertificado();
                                System.out.print("Opção: ");
                                opcao = sc.nextInt();
                                sc.nextLine();
                                switch (opcao) {
                                    case 1: {
                                        System.out.println("\nCertificados adquiridos: \n");
                                        for (Certificado certificado : usuario.getCertificados()){
                                            System.out.println(certificado.toString() + "\n");
                                        }
                                        break;
                                    }
                                    case 2: {
                                        System.out.println("\nUltimo certificado adquirido: \n");
                                        System.out.println(usuario.getCertificados().get(usuario.getCertificados().size()-1) + "\n");
                                        break;
                                    }
                                    case 3: {
                                        System.out.println("Voltar ao menu opções.");
                                        break;
                                    }
                                    default: {
                                        System.out.println("\n\u001B[31mOpção Inválida. Retornando ao menu principal.\u001B[0m\n");
                                    }
                                }
                                break;
                            }
                            case 5: {
                                System.out.println("\n" + usuario.imprimirDados() + "\n");
                                continue;
                            }
                            case 6: {
                                empresaService.listarTodos();
                                break;
                            }
                            case 7: {
                                System.out.println("Retornar ao Menu Principal");
                                break;
                            }
                            default: {
                                System.out.println("\n\u001B[31mOpção Inválida. Retornando ao menu principal.\u001B[0m\n");
                            }
                        }

                        break;
                    }
                    case 4: {
                        System.out.println("\nFinalizando a aplicação. Até logo!");
                        execucao = false;
                        break;
                    }
                    default:
                        System.out.println("\n\u001B[31mOpção Inválida. Tente novamente.\u001B[0m\n");
                }
            } catch (RuntimeException e) {
                System.out.println("\n\u001B[31mOpção Inválida. Retornando ao menu principal.\u001B[0m\n");
                sc.nextLine();
            }
        }
    }
}
import entities.*;
import entities.enums.Dificuldades;
import entities.enums.Games;
import services.*;
import utils.Cadastro;
import utils.Menu;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        EnderecoService enderecoService = new EnderecoService();
        ContatoService contatoService = new ContatoService();
        CertificadoService certificadoService = new CertificadoService();
        EscolaService escolaService = new EscolaService();
        EmpresaService empresaService = new EmpresaService();
        UsuarioService usuarioService = new UsuarioService();
        SoftSkillService softSkillService = new SoftSkillService();
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
                                int acertos = 0;
                                int totalQuestoes = 0;
                                for (int i = 0; i < 3; i++) {
                                    System.out.println("dificuldade: " + Dificuldades.valueOf(i));
                                    List<Portugues> questoes = portuguesService.listarPelaDificuldade(i);
                                    totalQuestoes = questoes.size();
                                    Portugues questao = questoes.get(random.nextInt(totalQuestoes));
                                    System.out.println(questao);
                                    while (!opcaoQuestao.matches("[A-Ea-e]")) {
                                        System.out.print("Opção: ");
                                        opcaoQuestao = sc.nextLine().toUpperCase().trim();
                                    }

                                    acertos += menu.validarQuestao(opcaoQuestao, questao, usuario);
                                    opcaoQuestao = "z";
                                }
                                if ((double) ((acertos * 100) / totalQuestoes) >= 66) {
                                    Certificado certificado = new Certificado(Games.PORTUGUES, LocalDateTime.now(), usuario);
                                    usuario.getCertificados().add(certificado);
                                    System.out.println("Parabéns pelo seu certificado!\n");
                                }
                                break;
                            }
                            case 2: {
                                int acertos = 0;
                                int totalQuestoes = 0;
                                for (int i = 0; i < 3; i++) {

                                    List<Matematica> questoes = matematicaService.listarPelaDificuldade(Dificuldades.valueOf(i));
                                    totalQuestoes = questoes.size();
                                    Matematica questao = questoes.get(random.nextInt(totalQuestoes));
                                    System.out.println(questao);

                                    while (!opcaoQuestao.matches("[A-Ea-e]")) {
                                        System.out.print("Opção: ");
                                        opcaoQuestao = sc.nextLine().toUpperCase().trim();
                                    }
                                    acertos += menu.validarQuestao(opcaoQuestao, questao, usuario);
                                    opcaoQuestao = "";
                                }
                                if ((double) ((acertos * 100) / totalQuestoes) >= 66) {
                                    Certificado certificado = new Certificado(Games.MATEMATICA, LocalDateTime.now(), usuario);
                                    usuario.getCertificados().add(certificado);
                                    System.out.println("Parabéns pelo seu certificado!\n");
                                }
                                break;
                            }
                            case 3: {
                                int acertos = 0;
                                int totalQuestoes = 0;
                                for (int i = 0; i < 3; i++) {

                                    List<SoftSkill> questoes = softSkillService.listarPelaDificuldade(Dificuldades.valueOf(i));
                                    totalQuestoes = questoes.size();
                                    SoftSkill questao = questoes.get(random.nextInt(totalQuestoes));
                                    System.out.println(questao);

                                    while (!opcaoQuestao.matches("[A-Ea-e]")) {
                                        System.out.print("Opção: ");
                                        opcaoQuestao = sc.nextLine().toUpperCase().trim();
                                    }
                                    acertos += menu.validarQuestao(opcaoQuestao, questao, usuario);
                                    opcaoQuestao = "";
                                }
                                if ((double) ((acertos * 100) / totalQuestoes) >= 66) {
                                    Certificado certificado = new Certificado(Games.SOFT_SKILLS, LocalDateTime.now(), usuario);
                                    usuario.getCertificados().add(certificado);
                                    System.out.println("Parabéns pelo seu certificado!\n");
                                }
                                break;
                            }
                            default:
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
                                List<Usuario> ranking = usuarioService.rankearUsuarios();
                                ranking.forEach(System.out::println);
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
                        break;
                    }
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
                                        if (usuario.getEndereco() != null) {
                                            System.out.println("ENDEREÇO CADASTRADO");
                                            System.out.println(usuario.getEndereco());
                                        } else {
                                            System.out.println("\nNenhum endereço cadastrado.\n");
                                        }
                                        continue;
                                    }
                                    case 2: {
                                        System.out.println();

                                        System.out.println("CADASTRO DE UM NOVO ENDEREÇO");
                                        Endereco endereco = cadastro.cadastrarEndereco(sc);
                                        usuario.setEndereco(endereco);
                                        System.out.println("Endereço cadastrado com sucesso!\n");

                                        continue;
                                    }
                                    case 3: {
                                        //ATUALIZAR UM ENDEREÇO
                                        int i = 0;
                                        if (usuario.getEndereco() != null) {
                                            System.out.println("ATUALIZAR O ENDEREÇO");
                                            enderecoService.listarPorDono(usuario.getId());
                                            System.out.print("Escolha um endereço pelo seu ID: ");
                                            int id = sc.nextInt();
                                            sc.nextLine();
                                            Endereco enderecoAtualizado = cadastro.cadastrarEndereco(sc);
                                            enderecoService.atualizar(id, enderecoAtualizado);
                                            System.out.println("Endereço atualizado com sucesso!\n");
                                        } else {
                                            System.out.println("Você não tem nenhum endereço cadastrado.\n");
                                        }
                                        break;
                                    }
                                    case 4: {
                                        //DELETAR UM ENDEREÇO
                                        int i = 0;
                                        if (usuario.getEndereco() != null) {
                                            System.out.println("DELETAR ENDEREÇO");
                                            enderecoService.listarPorDono(usuario.getId());
                                            System.out.print("Escolha o id do seu endereço: ");
                                            int id = sc.nextInt();
                                            Endereco endereco = enderecoService.listarPorDono(id);
                                            enderecoService.deletar(endereco);
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
                                        usuario.setContato(contatoService.listarPorDono(usuario.getId()));
                                        if (usuario.getContato() != null) {
                                            System.out.println("CONTATO CADASTRADO");
                                        } else {
                                            System.out.println("\nNenhum contato cadastrado.\n");
                                        }
                                        continue;
                                    }
                                    case 2: {
                                        Contato contato = cadastro.cadastrarContato(sc);
                                        System.out.println("Contato cadastrado com sucesso!\n");
                                        continue;
                                    }
                                    case 3: {
                                        int i = 0;

                                        System.out.println("Escolha o ID do contato a ser atualizado: ");
                                        System.out.print("Opção: ");
                                        opcao = sc.nextInt();
                                        sc.nextLine();
                                        Contato contatoAtualizado = cadastro.cadastrarContato(sc);
                                        break;
                                    }
                                    case 4: {
                                        int i = 0;

                                        System.out.println("Escolha o ID do contato a ser deletado: ");
                                        System.out.print("Opção: ");
                                        opcao = sc.nextInt();

                                    }
                                    case 5: {
                                        continue;
                                    }
                                    default: {
                                        System.out.println("\n\u001B[31mOpção Inválida. Retornando ao menu principal.\u001B[0m\n");
                                        continue;
                                    }
                                }
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
                                        } else {
                                            Escola escola = escolaService.listarPorId(opcao);
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
                                        for (Certificado certificado : usuario.getCertificados()) {
                                            System.out.println(certificado.toString() + "\n");
                                        }
                                        break;
                                    }
                                    case 2: {
                                        System.out.println("\nUltimo certificado adquirido: \n");
                                        System.out.println(usuario.getCertificados().get(usuario.getCertificados().size() - 1) + "\n");
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
                e.printStackTrace();
                sc.nextLine();
            }
        }
    }
}
import entities.*;
import exceptions.BancoDeDadosException;
import services.*;
import utils.Cadastro;
import utils.Menu;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws BancoDeDadosException {

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
        Menu menu = new Menu();
        Scanner sc = new Scanner(System.in);
        Usuario usuario = new Usuario();
        int opcao = 0;
        boolean execucao = true;
        String opcaoQuestao = "";


        System.out.println("BEM VINDOS AO EDUMATCH");

        menu.menuLogin();
        System.out.print("Digite sua opção: ");
        opcao = sc.nextInt();
        sc.nextLine();
        switch (opcao){
            case 1:
                    System.out.print("Digite seu email: ");
                    String email = sc.nextLine();

                    System.out.print("Digite sua senha: ");
                    String senha = sc.nextLine();
                    usuario = cadastro.Login(email, senha);
                    Endereco endereco = enderecoService.listarPorDono(usuario.getId());
                    if(endereco != null){
                      usuario.setEndereco(endereco);
                    } 
                    Contato contato = contatoService.listarPorDono(usuario.getId());
                    if(contato != null){
                      usuario.setContato(contato);
                    }
                    

                break;
            case 2:
                usuario = cadastro.cadastrarUsuario(sc);
                usuarioService.salvar(usuario);
                System.out.println();

                break;
            case 3:
                System.out.println("\nFinalizando a aplicação. Até logo!");
                execucao = false;
                break;
        }

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
                                int trilha = opcao-1;
                                int acertos = 0;
                                int totalQuestoes = 0;
                                for (int i = 0; i < 3; i++) {
                                    Portugues questao = portuguesService.listarPelaDificuldade(i);
                                    totalQuestoes++;
                                    System.out.println(questao);
                                    while (!opcaoQuestao.matches("[A-Ea-e]")) {
                                        System.out.print("Opção: ");
                                        opcaoQuestao = sc.nextLine().toUpperCase().trim();
                                    }

                                    acertos += menu.validarQuestao(opcaoQuestao, questao, usuario);
                                    opcaoQuestao = "";
                                }
                                Certificado certificado = cadastro.validarCertificado(acertos, totalQuestoes, usuario, trilha);
                                if (certificado != null) {
                                    certificadoService.salvar(certificado);
                                } else {
                                    System.out.println("Você não conseguiu seu certificado desta vez. Acerte mais questões para conseguir!");
                                }
                                usuarioService.atualizar(usuario.getId(), usuario);
                                break;
                            }
                            case 2: {
                                int trilha = opcao-1;
                                int acertos = 0;
                                int totalQuestoes = 0;
                                for (int i = 0; i < 3; i++) {
                                    Matematica questao = matematicaService.listarPelaDificuldade(i);
                                    totalQuestoes++;
                                    System.out.println(questao);
                                    while (!opcaoQuestao.matches("[A-Ea-e]")) {
                                        System.out.print("Opção: ");
                                        opcaoQuestao = sc.nextLine().toUpperCase().trim();
                                    }

                                    acertos += menu.validarQuestao(opcaoQuestao, questao, usuario);
                                    opcaoQuestao = "";
                                }
                                Certificado certificado = cadastro.validarCertificado(acertos, totalQuestoes, usuario, trilha);
                                if (certificado != null) {
                                    certificadoService.salvar(certificado);
                                } else {
                                    System.out.println("Você não conseguiu seu certificado desta vez. Acerte mais questões para conseguir!");
                                }
                                usuarioService.atualizar(usuario.getId(), usuario);

                                break;
                            }
                            case 3: {
                                int acertos = 0;
                                int totalQuestoes = 0;
                                int trilha = opcao-1;
                                for (int i = 0; i < 3; i++) {
                                    SoftSkill questao = softSkillService.listarPelaDificuldade(i);
                                    totalQuestoes++;
                                    System.out.println(questao);
                                    while (!opcaoQuestao.matches("[A-Ea-e]")) {
                                        System.out.print("Opção: ");
                                        opcaoQuestao = sc.nextLine().toUpperCase().trim();
                                    }

                                    acertos += menu.validarQuestao(opcaoQuestao, questao, usuario);
                                    opcaoQuestao = "";
                                }
                                Certificado certificado = cadastro.validarCertificado(acertos, totalQuestoes, usuario, trilha);
                                if (certificado != null) {
                                    certificadoService.salvar(certificado);
                                } else {
                                    System.out.println("Você não conseguiu seu certificado desta vez. Acerte mais questões para conseguir!");
                                }
                                usuarioService.atualizar(usuario.getId(), usuario);
                                break;
                            }
                            default:
                                System.out.println("\nRetornando ao menu principal.\n");
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
                                        endereco.setId_usuario(usuario.getId());
                                        enderecoService.salvar(endereco);
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
                                        escolaService.listarTodos();
                                        System.out.print("Escolha o ID de uma escola para se cadastrar (Digite o número 0 caso não tenha na lista): ");
                                        opcao = sc.nextInt();
                                        sc.nextLine();
                                        if (opcao == 0) {
                                            Escola escola = cadastro.cadastrarEscola(sc);
                                            escolaService.salvar(escola);
                                            usuario.setId_escola(escola.getId());
                                            usuarioService.atualizar(usuario.getId(), usuario);
                                        } else {
                                            int escola_id = escolaService.listarPorId(opcao).getId();
                                            usuario.setId_escola(escola_id);
                                            usuarioService.atualizar(usuario.getId(), usuario);
                                        }
                                        continue;
                                    }
                                    case 3: {
                                        continue;
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
                                        certificadoService.listarPorUsuario(usuario);

                                        break;
                                    }
                                    case 2: {
                                        System.out.println("\nUltimo certificado adquirido:");
                                        System.out.println(certificadoService.listarUltimo(usuario));
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
                                System.out.println("\nRetornar ao Menu Principal\n");
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
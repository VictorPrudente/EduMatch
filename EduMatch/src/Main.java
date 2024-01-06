import entities.Escola;
import entities.Usuario;
import services.EscolaService;
import services.UsuarioService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        EscolaService escolaService = new EscolaService();
        escolaService.inicializarLista();

        int selecaoMenu=0;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("[1] Novo jogo\n" +
                    "[2] Ranking\n" +
                    "[3] Opções\n" +
                    "[6] Sair");
            selecaoMenu = teclado.nextInt();

            switch (selecaoMenu) {
                case 1: {
                    int selecaoJogo=0;
                    do {
                        System.out.println("Escolha uma trilha\n" +
                                "[1] Português\n" +
                                "[2] Matemática\n" +
                                "[3] SoftSkills\n" +
                                "[4] Retornar ao Menu Principal");
                        selecaoJogo = teclado.nextInt();

                        switch (selecaoJogo){
                            case 1: {
                                //TODO Português
                                break;
                            }
                            case 2: {
                                //TODO Matemática
                                break;
                            }
                            case 3: {
                                //TODO SoftSkills
                                break;
                            }
                            case 4: {
                                System.out.println("Retornar ao Menu Principal");
                                break;
                            }
                            default: {
                                System.out.println("Opção Inválida!");
                            }

                        }

                    }while(selecaoJogo != 4);
                    break;
                }
                case 2: {
                    int selecaoRanking = 0;
                    do {
                        System.out.println("Ranking\n" +
                                "[1] Minha pontuação\n" +
                                "[2] Posição geral\n" +
                                "[3] Voltar ao menu principal");

                        selecaoRanking= teclado.nextInt();

                        switch (selecaoRanking) {
                            case 1: {
                                //TODO Minha pontuacao
                                break;
                            }
                            case 2: {
                                //TODO Posicao geral
                                break;
                            }
                            case 3: {
                                System.out.println("Retornar ao Menu Principal");
                                break;
                            }
                            default: {
                                System.out.println("Opção Inválida!");
                            }
                        }
                    } while (selecaoRanking != 3);

                    break;
                }
                case 3: {
                    int selecaoOpcoes = 0;
                    do {
                        System.out.println("Menu Opcões\n" +
                                "[1] Endereços\n" +
                                "[2] Contatos\n" +
                                "[3] Escola\n" +
                                "[4] Listar Certificados\n" +
                                "[5] Mostrar dados do usuário\n" +
                                "[6] Listar escolas cadastradas\n" +
                                "[7] Listar empresas cadastradas\n" +
                                "[8] Voltar ao Menu Principal");

                        selecaoOpcoes= teclado.nextInt();

                        switch (selecaoOpcoes) {
                            case 1: {
                                //TODO Endereços
                                break;
                            }
                            case 2: {
                                //TODO Contatos
                                break;
                            }
                            case 3: {
                                //TODO Escola
                                break;
                            }
                            case 4: {
                                int selecaoCertificados = 0;
                                do {
                                    System.out.println("Menu Certificados\n" +
                                            "[1] Listar certificados\n" +
                                            "[2] Listar ultimo certificado adquirido\n" +
                                            "[3] Voltar ao menu opções");

                                    selecaoCertificados=teclado.nextInt();

                                    switch (selecaoCertificados) {
                                        case 1: {
                                            //TODO Listar certificados
                                            break;
                                        }
                                        case 2: {
                                            //TODO Listar ultimo certificado adquirido
                                            break;
                                        }
                                        case 3: {
                                            System.out.println("Voltar ao menu opções.");
                                            break;
                                        }
                                        default: {
                                            System.out.println("Opção Inválida!");
                                        }
                                    }

                                }while (selecaoCertificados != 3);
                                break;
                            }
                            case 5: {
                                //TODO Mostrar dados do usuário
                                break;
                            }
                            case 6: {
                                int selecaoEscolas = 0;
                                do {
                                    System.out.println("Menu Escolas\n" +
                                            "[1] Listar Escolas\n" +
                                            "[2] Cadastrar em uma escola\n" +
                                            "[3] Voltar ao menu principal");

                                     selecaoEscolas= teclado.nextInt();
                                    switch (selecaoEscolas) {
                                        case 1: {
                                            ArrayList<Escola> lista=escolaService.listar();
                                            for (Escola escola: lista) {
                                                System.out.println(escola.toString());
                                            }
                                            break;
                                        }
                                        case 2: {
                                            //TODO Cadastrar uma escola
                                            break;
                                        }
                                        case 3: {
                                            System.out.println("Voltar ao menu principal");
                                            break;
                                        }
                                        default: {
                                            System.out.println("Opção Inválida!");
                                        }
                                    }
                                } while (selecaoEscolas != 3);
                                break;
                            }
                            case 7: {
                                //TODO Listar empresas cadastradas
                                break;
                            }
                            case 8: {
                                System.out.println("Retornar ao Menu Principal");
                                break;
                            }
                            default: {
                                System.out.println("Opção Inválida!");
                            }
                        }
                    } while (selecaoOpcoes !=8);

                    break;
                }
                case 6: {
                    System.out.println("Sair do jogo");
                    break;
                }
                default: {
                    System.out.println("Opção Inválida!");
                }
            }
        }while (selecaoMenu != 6);





        /*
        *
        *
        *
        * Menu de boas vindas
Cadastro do usuario -> Validar CPF?

Menu novo jogo (opcao 1)


Menu questões:
(validar se o usuário já respondeu alguma questão para não repeti-la / caso retorne ao menu principal, retornar a ultima questão ao voltar.)
~Corpo da questão~
[1] Corpo da letra A
[2] Corpo da letra B
[3] Corpo da letra C
[4] Corpo da letra D
[5] Corpo da letra E
[6] Retornar ao menu principal

(Repete x vezes)
(Imprime resultado)
(emite certificado)
(retorna ao menu principal)
-------------------------------


Menu Endereço
[1] Listar Endereços cadastrados
[2] Cadastrar novo endereço
[3] Atualizar um endereço
[4] Deletar um endereço
[5] Retornar ao menu opções

Menu Contatos
[1] Listar Contatos cadastrados
[2] Listar contatos por tipo > [1] Celulares / [2] Residenciais / [3] Comerciais
[3] Cadastrar novo contato
[4] Atualizar um endereço
[5] Deletar um endereço
[6] Retornar ao menu opções

Menu Escolas
[1] Listar Escolas
[2] Cadastrar em uma escola
[3] Voltar ao menu principal



--------------------------------------

-------------------------------------
        *
        * */
    }
}
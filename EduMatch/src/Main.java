import entities.SoftSkill;
import entities.Usuario;
import entities.enums.Dificuldades;
import services.*;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        EscolaService escolaService = new EscolaService();
        UsuarioService usuarioService = new UsuarioService();
        SoftSkillService softSkillService = new SoftSkillService();
        EnderecoService enderecoService = new EnderecoService();
        EmpresaService empresaService = new EmpresaService();
        ContatoService contatoService = new ContatoService();
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        String opcaoQuestao;


        System.out.println("BEM VINDOS AO EDUMATCH");
        System.out.print("Por favor, digite seu nome: ");
        String nome = sc.nextLine();
        System.out.print("Por favor, digite Sobrenome: ");
        String sobrenome = sc.nextLine();
        System.out.print("Agora, digite seu CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Por favor, Digite sua idade: ");
        int idade = sc.nextInt();
        sc.nextLine();
        Usuario usuario = new Usuario(nome, sobrenome, cpf, idade, 0);
        usuarioService.salvar(usuario);

        System.out.println();
        System.out.println();
        while (true) {
            System.out.println("MENU PRINCIPAL");
            System.out.println("""
                    [1] Novo jogo
                    [2] Ranking
                    [3] Opções
                    [4] Sair""");
            System.out.print("Digite sua opção: ");
            opcao = sc.nextInt();
            switch (opcao) {
                case 1: {
                    System.out.println();
                    System.out.println("""
                            Escolha uma trilha
                            [1] Português
                            [2] Matemática
                            [3] SoftSkills
                            [4] Retornar ao Menu Principal""");
                    System.out.print("Digite sua opção: ");
                    opcao = sc.nextInt();
                    sc.nextLine();
                    switch (opcao){
                        case 1: {
                            break;
                        }
                        case 2: {
                            break;
                        }
                        case 3: {
                            System.out.println();
                            for (int i = 0; i<3; i++){
                            SoftSkill questao = softSkillService.listarPelaDificuldade(Dificuldades.valueOf(i)).get(1);

                            System.out.println(questao);

                            System.out.print("Opção: ");
                            opcaoQuestao = sc.nextLine().toUpperCase();

                            if (opcaoQuestao.equals(questao.getOpcaoCerta())){
                                usuario.setPontuacao(questao.getPontos());
                                System.out.println("Opção correta! Isso ai :D");
                            } else {
                                System.out.println("Opção errada, preste mais atenção!");
                            }}
                            break;
                        }
                    }
                break;
                }
                case 2: {
                    int selecaoRanking = 0;
                    do {
                        System.out.println("Ranking\n" +
                                "[1] Minha pontuação\n" +
                                "[2] Posição geral\n" +
                                "[3] Voltar ao menu principal");

                        selecaoRanking = sc.nextInt();

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

                        selecaoOpcoes = sc.nextInt();
                        sc.nextLine();

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

                                    selecaoCertificados = sc.nextInt();

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

                                } while (selecaoCertificados != 3);
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

                                    selecaoEscolas = sc.nextInt();
                                    switch (selecaoEscolas) {
                                        case 1: {
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
                    } while (selecaoOpcoes != 8);

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
            break;
        }
        sc.close();
    }
}




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

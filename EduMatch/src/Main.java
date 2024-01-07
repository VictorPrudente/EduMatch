import entities.Matematica;
import entities.Portugues;
import entities.SoftSkill;
import entities.Usuario;
import entities.enums.Dificuldades;
import services.*;
import utils.Menu;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        EscolaService escolaService = new EscolaService();
        UsuarioService usuarioService = new UsuarioService();
        SoftSkillService softSkillService = new SoftSkillService();
        EnderecoService enderecoService = new EnderecoService();
        EmpresaService empresaService = new EmpresaService();
        ContatoService contatoService = new ContatoService();
        PortuguesService portuguesService = new PortuguesService();
        MatematicaService matematicaService = new MatematicaService();
        Random random = new Random();
        Menu menu = new Menu();
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        boolean execucao = true;
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

        while (execucao) {
            menu.menuPrincipal();
            System.out.print("Digite sua opção: ");
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
                            for (int i = 0; i<3; i++){
                                Portugues questao = portuguesService.listarPelaDificuldade(Dificuldades.valueOf(i)).get(random.nextInt(0, 2));
                                System.out.println(questao);

                                System.out.print("Opção: ");
                                opcaoQuestao = sc.nextLine().toUpperCase().trim();
                                if (opcaoQuestao.equals(questao.getOpcaoCerta())) {
                                    usuario.setPontuacao(questao.getPontos());
                                    System.out.println("Opção correta! Isso ai :D");
                                } else {
                                    System.out.println("Opção errada, preste mais atenção!");
                                }
                            }
                            break;
                        }
                        case 2: {
                            for(int i = 0; i < 3; i++) {
                                Matematica questao = matematicaService.listarPelaDificuldade(Dificuldades.valueOf(i)).get(random.nextInt(0, 2));
                                System.out.println(questao);
                                System.out.print("Opção: ");
                                opcaoQuestao = sc.nextLine().toUpperCase().trim();
                                if (opcaoQuestao.equals(questao.getOpcaoCerta())) {
                                    usuario.setPontuacao(questao.getPontos());
                                    System.out.println("Opção correta! Isso ai :D");
                                } else {
                                    System.out.println("Opção errada, preste mais atenção!");
                                }
                            }
                            break;
                        }
                        case 3: {
                            System.out.println();
                            for (int i = 0; i < 3; i++) {
                                SoftSkill questao = softSkillService.listarPelaDificuldade(Dificuldades.valueOf(i)).get(i);
                                System.out.println(questao);

                                System.out.print("Opção: ");
                                opcaoQuestao = sc.nextLine();

                                if (opcaoQuestao.equals(questao.getOpcaoCerta())) {
                                    usuario.setPontuacao(questao.getPontos());
                                    System.out.println("Opção correta! Isso ai :D");
                                } else {
                                    System.out.println("Opção errada, preste mais atenção!");
                                }
                            }
                            break;
                        }
                    }
                    continue;
                }
                //RANKING
                case 2: {
                    menu.menuRanking();
                    System.out.print("Opção: ");
                    opcao = sc.nextInt();
                    switch (opcao) {
                        case 1: {
                            System.out.println();
                            System.out.println("Sua pontuação é de: " + usuario.getPontuacao());
                            continue;
                        }
                        case 2: {
                            usuarioService.rankearUsuarios();
                            System.out.println();
                            continue;
                        }
                        case 3: {
                            System.out.println("Retornando ao Menu Principal");
                            continue;
                        }
                        default: {
                            System.out.println("Opção Inválida!");
                        }
                    }
                }
                break;
                //OPÇÕES
                case 3: {
                    menu.menuOpcoes();
                    System.out.print("Opção: ");
                    opcao = sc.nextInt();

                    switch (opcao) {
                        case 1: {
                            menu.menuEndereco();
                            System.out.print("Opção: ");
                            opcao = sc.nextInt();
                            switch (opcao){
                                case 1:{
                                    //TODO Usuario Endereço
                                    continue;
                                }
                                case 2:{
                                    //CADASTRAR NOVO ENDEREÇO
                                    continue;
                                }
                                case 3:{
                                    //ATUALIZAR UM ENDEREÇO
                                }
                                case 4:{
                                    //DELETAR UM ENDEREÇO
                                }
                                case 5:{
                                    continue;
                                }
                            }
                            continue;
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

                    break;
                }
                case 4: {
                    System.out.println("Finalizando a aplicação. Até logo!");
                    execucao = false;
                    break;
                }
                default: {
                    System.out.println("Opção Inválida!");
                }
                break;
            }
        }
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

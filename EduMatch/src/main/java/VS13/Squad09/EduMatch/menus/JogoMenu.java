package VS13.Squad9.EduMatch.menu;

import entities.*;
import entities.enums.Dificuldades;
import entities.enums.Games;
import services.MatematicaService;
import services.PortuguesService;
import services.SoftSkillService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class JogoMenu {
    MatematicaService matematicaService = new MatematicaService();
    PortuguesService portuguesService = new PortuguesService();
    SoftSkillService softSkillService = new SoftSkillService();

    private int acertos = 0;
    private int totalQuestoes = 0;

    private static final double porcentagemValidacao = 66.0;

    private Game questao;

    public Certificado jogar(Scanner sc, int opcao, Usuario usuario){
        opcao--;
        String respostaUsuario;
        switch (opcao){

            case 0:
                for (Dificuldades dificuldade : Dificuldades.values()) {
                    questao = portuguesService.listarPelaDificuldade(dificuldade.ordinal());
                    System.out.println(questao);
                    respostaUsuario = validarEntrada(sc);
                    validarResposta(respostaUsuario, usuario);
                }
                break;

            case 1:
                for (Dificuldades dificuldade : Dificuldades.values()) {
                    questao = matematicaService.listarPelaDificuldade(dificuldade.ordinal());
                    System.out.println(questao);
                    respostaUsuario = validarEntrada(sc);
                    validarResposta(respostaUsuario, usuario);
                }
                break;

            case 2:
                for (Dificuldades dificuldade : Dificuldades.values()) {
                    questao = softSkillService.listarPelaDificuldade(dificuldade.ordinal());
                    System.out.println(questao);
                    respostaUsuario = validarEntrada(sc);
                    validarResposta(respostaUsuario, usuario);
                }
                break;
            case 3:
                System.out.println("\nRetornando ao menu principal.");
                break;

            default:
                System.out.println("Opção inválida. Retornando ao menu principal.");
                break;
        }

        if(aprovado()){
            resetarValores();
            return new Certificado(Games.valueOf(opcao), LocalDateTime.now(), usuario);
        }
        else {
            resetarValores();
            return null;
        }
    }

    private String validarEntrada(Scanner sc){
        String respostaUsuario = "";
        while (!respostaUsuario.matches("[A-Ea-e]")) {
            System.out.print("Opção: ");
            respostaUsuario = sc.nextLine().toUpperCase().trim();
        }
        return respostaUsuario;
    }

    private void validarResposta(String respostaUsuario, Usuario usuario) {
        if (respostaUsuario.equals(questao.getOpcaoCerta())) {
            System.out.println("\nOpção correta! Isso ai :D\n");
            acertos++;
            totalQuestoes++;
            usuario.setPontuacao(questao.getPontos());
        } else {
            System.out.println("\nOpção errada, preste mais atenção!\n");
            totalQuestoes++;
        }
    }

    private boolean aprovado() {
        double aprovado = (double) acertos * 100 / totalQuestoes;
        return aprovado >= porcentagemValidacao;
    }
    private void resetarValores(){
        acertos = 0;
        totalQuestoes = 0;
    }
}

package utils;

import entities.Contato;
import entities.Endereco;
import entities.Game;
import entities.Usuario;
import entities.enums.TipoDeContato;

import java.util.Scanner;

public class Menu {


    public void validarQuestao(String opcaoRespondida, Game questao, Usuario usuario){
        if (opcaoRespondida.equals(questao.getOpcaoCerta())) {
            usuario.setPontuacao(questao.getPontos());
            System.out.println("Opção correta! Isso ai :D");
        } else {
            System.out.println("Opção errada, preste mais atenção!");
        }
    }

    public void menuPrincipal(){

        StringBuilder sb = new StringBuilder();

        sb.append("MENU PRINCIPAL\n");
        sb.append("[1] Novo jogo\n");
        sb.append("[2] Ranking\n");
        sb.append("[3] Opções\n");
        sb.append("[4] Sair da aplicação");
        System.out.println(sb);
    }

    public void menuNovoJogo(){

        StringBuilder sb = new StringBuilder();

        sb.append("Escolha uma trilha\n");
        sb.append("[1] Português\n");
        sb.append("[2] Matemática\n");
        sb.append("[3] SoftSkills\n");
        sb.append("[4] Retornar ao Menu Principal");
        System.out.println(sb);
    }

    public void menuOpcoes(){

        StringBuilder sb = new StringBuilder();

        sb.append("\nMENU OPÇÕES\n");
        sb.append("[1] Endereços\n");
        sb.append("[2] Contatos\n");
        sb.append("[3] Escola\n");
        sb.append("[4] Listar Certificados\n");
        sb.append("[5] Mostrar dados do usuário\n");
        sb.append("[6] Listar escolas cadastradas\n");
        sb.append("[7] Voltar ao Menu Principal");
        System.out.println(sb);
    }

    public void menuEndereco(){

        StringBuilder sb = new StringBuilder();
        sb.append("\nMENU ENDEREÇOS\n");
        sb.append("[1] Listar Endereços cadastrados\n");
        sb.append("[2] Cadastrar novo endereço\n");
        sb.append("[3] Atualizar um endereço\n");
        sb.append("[4] Deletar um endereço\n");
        sb.append("[5] Retornar ao menu opções");
        System.out.println(sb);
    }

    public void menuContato(){

        StringBuilder sb = new StringBuilder();
        sb.append("\nMENU CONTATOS\n");
        sb.append("[1] Listar Contatos cadastrados\n");
        sb.append("[2] Cadastrar novo contato\n");
        sb.append("[3] Atualizar um contato\n");
        sb.append("[4] Deletar um contato\n");
        sb.append("[5] Retornar ao menu opções");
        System.out.println(sb);
    }

    public void menuEscola(){

        StringBuilder sb = new StringBuilder();

        sb.append("\n[1] Listar Escolas\n");
        sb.append("[2] Cadastrar em uma escola\n");
        sb.append("[3] Voltar ao menu principal");
        System.out.println(sb);
    }

    public void menuCertificado(){

        StringBuilder sb = new StringBuilder();

        sb.append("\n[1] Listar certificados\n");
        sb.append("[2] Listar ultimo certificado adquirido\n");
        sb.append("[3] Voltar ao menu opções");

        System.out.println(sb);
    }

    public void menuRanking(){

        StringBuilder sb = new StringBuilder();

        sb.append("\n[1] Minha pontuação\n");
        sb.append("[2] Posição geral\n");
        sb.append("[3] Voltar ao menu principal");

        System.out.println(sb);
    }


}

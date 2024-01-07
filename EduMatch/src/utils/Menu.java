package utils;

import java.util.Scanner;

public class Menu {

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

        sb.append("MENU OPÇÕES\n");
        sb.append("[1] Contatos\n");
        sb.append("[2] Escola\n");
        sb.append("[3] Listar Certificados\n");
        sb.append("[4] Mostrar dados do usuário\n");
        sb.append("[5] Listar escolas cadastradas\n");
        sb.append("[6] Voltar ao Menu Principal");
        System.out.println(sb);
    }

    public void menuEndereco(){

        StringBuilder sb = new StringBuilder();

        sb.append("[1] Listar Endereços cadastrados\n");
        sb.append("[2] Cadastrar novo endereço\n");
        sb.append("[3] Atualizar um endereço\n");
        sb.append("[4] Deletar um endereço\n");
        sb.append("[5] Retornar ao menu opções");
        System.out.println(sb);
    }

    public void menuContato(){

        StringBuilder sb = new StringBuilder();

        sb.append("[1] Listar Contatos cadastrados\n");
        sb.append("[2] Cadastrar novo contato\n");
        sb.append("[3] Atualizar um contato\n");
        sb.append("[4] Deletar um contato\n");
        sb.append("[5] Retornar ao menu opções");
        System.out.println(sb);
    }

    public void menuEscola(){

        StringBuilder sb = new StringBuilder();

        sb.append("[1] Listar Escolas\n");
        sb.append("[2] Cadastrar em uma escola\n");
        sb.append("[3] Voltar ao menu principal");
        System.out.println(sb);
    }

    public void menuCertificado(){

        StringBuilder sb = new StringBuilder();

        sb.append("[1] Listar certificados\n");
        sb.append("[2] Listar ultimo certificado adquirido\n");
        sb.append("[3] Voltar ao menu opções");

        System.out.println(sb);
    }

    public void menuRanking(){

        StringBuilder sb = new StringBuilder();

        sb.append("[1] Minha pontuação\n");
        sb.append("[2] Posição geral\n");
        sb.append("[3] Voltar ao menu principal");

        System.out.println(sb);
    }

}

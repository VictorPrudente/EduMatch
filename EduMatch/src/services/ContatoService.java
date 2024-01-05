package services;

import entities.Contato;

import java.util.ArrayList;
import java.util.Scanner;

public class ContatoService {
    private ArrayList<Contato> contatos = new ArrayList<>();
    public ContatoService(){
        Contato contatoUm=new Contato("Principal", "333000333", 1);
        Contato contatoDois=new Contato("Principal", "983220021", 2);
        Contato contatoTres=new Contato("Principal", "6642167", 1);
        contatos.add(contatoUm);
        contatos.add(contatoDois);
        contatos.add(contatoTres);
    }
    public void salvar (){
        Scanner teclado = new Scanner(System.in);
        System.out.println("Descrição: ");
        String descricao = teclado.next();

        System.out.println("Digite telefone: ");
        String telefone = teclado.next();

        System.out.println("Tipo (1 Residencial / 2 Comercial): ");
        int tipo = teclado.nextInt();

        Contato contato=new Contato(descricao, telefone, tipo);

        contatos.add(contato);
    }
    public ArrayList<Contato> listarTodos() {
        return contatos;
    }
    public boolean atualizar (int id, Contato contato){

        Contato atualizar=new Contato(id);

        if (contatos.contains(atualizar)){
            int p=contatos.indexOf(atualizar);
            contato=contatos.get(p);

            Scanner teclado = new Scanner(System.in);
            System.out.println("Digite a nova descrição: ");
            contato.setDescricao(teclado.next());

            System.out.println("Digite telefone: ");
            contato.setTelefone(teclado.next());

            System.out.println("Tipo (1 Residencial / 2 Comercial): ");
            contato.setTipo(teclado.nextInt());

            teclado.close();
            return true;
        }else{
            return false;
        }
    }
    public boolean deletar (int id){

        Contato delete=new Contato(id);
        if (contatos.contains(delete)){
            contatos.remove(delete);
            return true;
        }else{
            return false;
        }
    }

}

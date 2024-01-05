package services;

import entities.Contato;
import enums.TipoDeContato;

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
    //CREATE
    public boolean salvar (Contato contato){
        contatos.add(contato);
        return true;
    }
    //READ
    public ArrayList<Contato> listarTodos() {
        return contatos;
    }

    public Contato listarPorId(int id){
        for (Contato contato : contatos){
            if (contato.getId() == id){
                return contato;
            }
        }
        return null;
    }
    public ArrayList<Contato> listarPorTipo(TipoDeContato tipo){
        ArrayList <Contato> lista=new ArrayList<>();
        for (Contato contato: contatos){
            if (contato.getTipo()==tipo){
                lista.add(contato);
            }
        }return lista;
    }
    //UPDATE
    public boolean atualizar (int id, Contato contato){

        Contato atualizar=new Contato(id);

        if (contatos.contains(atualizar)){
            int p=contatos.indexOf(atualizar);
            contatos.set (p, contato);
            return true;
        }else{
            return false;
        }
    }
    //DELETE
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

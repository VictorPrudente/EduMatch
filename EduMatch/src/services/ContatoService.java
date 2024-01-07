package services;

import entities.Contato;
import entities.Empresa;
import entities.Usuario;
import entities.enums.TipoDeContato;
import interfaces.Service;

import java.util.ArrayList;

public class ContatoService implements Service<Contato> {
    private ArrayList<Contato> contatos = new ArrayList<>();
    public ContatoService(){
        Contato contatoUm=new Contato("Principal", "333000333", TipoDeContato.CELULAR);
        Contato contatoDois=new Contato("Principal", "983220021", TipoDeContato.RESIDENCIAL);
        Contato contatoTres=new Contato("Principal", "6642167", TipoDeContato.COMERCIAL);
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
    @Override
    public void listarTodos() {
        for (Contato contato : contatos){
            System.out.println(contato.toString());
        }
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
    @Override
    public boolean atualizar (int id, Contato contato){

        for (Contato contatoAtualizar : contatos) {
            if (contatoAtualizar.getId() == id) {
                contatoAtualizar.setDescricao(contato.getDescricao());
                contatoAtualizar.setTipo(contato.getTipo());
                contatoAtualizar.setTelefone(contato.getTelefone());
                return true;
            }
        }
        return false;
    }
    //DELETE
    @Override
    public boolean deletar (Contato contato){
        return contatos.remove(contato);
    }

}

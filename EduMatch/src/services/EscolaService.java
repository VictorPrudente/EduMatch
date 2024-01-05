package services;

import entities.Escola;

import java.util.ArrayList;

public class EscolaService {

    ArrayList<Escola> listaEscolas = new ArrayList<>();

    public ArrayList<Escola> listar(){
        return listaEscolas;
    }

    //CREATED
    public String adicionar(Escola escola) {
        if (listaEscolas.add(escola)) {
            return "Escola: " + escola.getNome() + " adicionada com sucesso!";
        } else {
            return "Não foi possível adicionar a escola" + escola.getNome();
        }
    }

    //READ
    public String buscar(String cnpj) {
        for (int i = 0; i < listaEscolas.size(); i++) {  //percorrendo a lista
            if (listaEscolas.get(i).getCnpj().equals(cnpj)) { //retorna objeto -
                return "A escola pesquisa é: " + listaEscolas.get(i).getNome();
            }
        }
        return "Escola com O CNPJ " + cnpj + " não encontrado!";
    }

    //UPDATED
    public String atualizar(Escola escola, String nomeEscola) {
        for (int i = 0; i < listaEscolas.size(); i++) {
            if (listaEscolas.get(i).equals(escola)) {
                listaEscolas.get(i).setNome(nomeEscola);
                return "Escola: " + escola.getNome() + " atualizada com sucesso!";
            }
        }
        return "Não foi possível atualizar o nome da escola: " + escola.getNome();
    }

    //DELETED
    public String deletar(Escola escola) {
        if (listaEscolas.remove(escola)) {
            return "Escola: " + escola.getNome() + " foi removida com sucesso!";
        }
        return "Não é possível remover a escola: " + escola.getNome();
    }
}

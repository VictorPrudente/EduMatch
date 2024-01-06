package services;

import entities.Escola;
import enums.TipoEscola;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class EscolaService {

    private AtomicInteger COUNTER = new AtomicInteger();
    Random random = new Random();

    ArrayList<Escola> listaEscolas = new ArrayList<>();
    public EscolaService(){
        inicializarLista();
    }

    public void inicializarLista(){
        Escola escola = new Escola("Garibaldi", TipoEscola.PRIVADA, "123456");
        escola.setId(COUNTER.incrementAndGet());
        listaEscolas.add(escola);
    }


    public ArrayList<Escola> listar() {
        return listaEscolas;
    }

    //CREATED
    public boolean adicionar(Escola escola) {
        for (Escola escola1 : listaEscolas) {
            if (escola1.getCnpj().equals(escola.getCnpj())) {
                System.out.println("O CNPJ " + escola.getCnpj() + "já foi cadastrada!");
                return false;
            }

        }
        listaEscolas.add(escola);
        System.out.println("Escola cadastrada com sucesso!");
        return true;
    }

    //READ
    public Escola buscar(String cnpj){
        for (Escola escola1 : listaEscolas){
            if (escola1.getCnpj().equals(cnpj)){
                return escola1;
            }
        }
        throw new NoSuchElementException("Escola com O CNPJ " + cnpj + "não encontrado!");
    }


    //UPDATED
    public String atualizar(Escola escola, String nomeEscola){
        for (int i = 0; i < listaEscolas.size(); i++){
            if (listaEscolas.get(i).equals(escola)){
                listaEscolas.get(i).setNome(nomeEscola);
                return "Escola atualizada com sucesso!";            }
        }
        throw new NoSuchElementException("Não foi possível atualizar o nome da escola: " + escola.getNome());
    }

    //DELETED
    public String deletar(Escola escola) {
        if (listaEscolas.remove(escola)) {
            return "Escola foi removida com sucesso!";
        }
        throw new NoSuchElementException("Escola com o ID: " + escola.getId() + " não encontrado!");
    }
}

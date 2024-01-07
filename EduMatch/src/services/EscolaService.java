package services;

import entities.Empresa;
import entities.Escola;
import entities.enums.TipoEscola;
import interfaces.Service;


import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class EscolaService implements Service<Escola> {

    private AtomicInteger COUNTER = new AtomicInteger();
    Random random = new Random();

    ArrayList<Escola> escolas = new ArrayList<>();
    public EscolaService(){
        inicializarLista();
    }

    public void inicializarLista(){
        Escola escola = new Escola("Garibaldi", TipoEscola.PRIVADA, "123456");
        escola.setId(COUNTER.incrementAndGet());
        escolas.add(escola);
    }


    public ArrayList<Escola> listar() {
        return escolas;
    }

    //CREATED
    @Override
    public boolean salvar(Escola escola) {
        for (Escola escola1 : escolas) {
            if (escola1.getCnpj().equals(escola.getCnpj())) {
                System.out.println("O CNPJ " + escola.getCnpj() + "já foi cadastrada!");
                return false;
            }

        }
        escolas.add(escola);
        System.out.println("Escola cadastrada com sucesso!");
        return true;
    }

    //READ
    @Override
    public void listarTodos(){
        for (Escola escola : escolas){
            System.out.println(escola.toString());
        }
    }



    //UPDATED
    @Override
    public boolean atualizar(int id, Escola escola) {
        for (Escola EscolaAtualizar : escolas) {
            if (EscolaAtualizar.getId() == id) {
                EscolaAtualizar.setNome(escola.getNome());
                EscolaAtualizar.setTipo(TipoEscola.valueOf(escola.getTipo()));
                return true;
            }
        }
        return false;
    }
    //DELETED
    @Override
    public boolean deletar(Escola escola) {
        return escolas.remove(escola);
    }
}

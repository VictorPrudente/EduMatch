package services;

import entities.Escola;
import entities.enums.TipoEscola;
import interfaces.Service;


import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class EscolaService implements Service<Escola> {

    public Escola listarPorId(int id) {
        for(Escola escola : escolas){
            if (escola.getId() == id){
                return escola;
            }
        } throw new NoSuchElementException("Escola com o id " + id + " não encontrada.");
    }

    //CREATED
    @Override
    public boolean salvar(Escola escola) {
        for (Escola escola1 : escolas) {
            if (escola1.getNome().equals(escola.getNome())) {
                System.out.println("A escola " + escola.getNome() + " já foi cadastrada!");
                return false;
            }

        }
        escola.setId(COUNTER.incrementAndGet());
        escolas.add(escola);
        System.out.println("Escola cadastrada com sucesso!\n");
        return true;
    }

    //READ
    @Override
    public void listarTodos(){
        for (Escola escola : escolas){
            System.out.println(escola.toString() + "\n");
        }
    }



    //UPDATED
    @Override
    public boolean atualizar(int id, Escola escola) {
        for (Escola EscolaAtualizar : escolas) {
            if (EscolaAtualizar.getId() == id) {
                EscolaAtualizar.setNome(escola.getNome());
                EscolaAtualizar.setTipo(escola.getTipo());
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

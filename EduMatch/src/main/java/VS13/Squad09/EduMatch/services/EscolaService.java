package services;

import entities.Endereco;
import entities.Escola;
import entities.enums.TipoEscola;
import exceptions.BancoDeDadosException;
import interfaces.Service;
import repository.EscolaRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class EscolaService implements Service<Escola>{
    private EscolaRepository escolaRepository;

    public EscolaService(){
        escolaRepository = new EscolaRepository();
    }

    public Escola listarPorId(int id) {
        try {
            Escola escola = escolaRepository.listarPorId(id);
            return escola;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        System.out.printf("Escola com o ID:%d não econtrada.", id);
        return null;
    }

    //CREATED
    public boolean salvar(Escola escola) {
        try {
            escolaRepository.adicionar(escola);
            System.out.println("Escola adicinada com sucesso! " + escola);
            return true;
        } catch (BancoDeDadosException e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Escola não cadastrada. Tente novamente.");
        return false;
    }

    //READ
    public void listarTodos(){
        try {
            List<Escola> listar = escolaRepository.listar();
            listar.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public boolean atualizar(int id, Escola escola) {
        try {
            escolaRepository.editar(id, escola);
            System.out.printf("Escola com o ID %d atualizada.", id);
            return true;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        System.out.println("Escola não atualizada.");
        return false;
    }

    @Override
    public boolean deletar(int id) {
        try {
            escolaRepository.remover(id);
            System.out.println("Escola com o id %d removida com sucesso.");
            return true;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        System.out.println("Escola não removida.");
        return false;
    }
}

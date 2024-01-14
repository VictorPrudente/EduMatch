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

public class EscolaService {
    private EscolaRepository escolaRepository;

    public EscolaService(){
        escolaRepository = new EscolaRepository();
    }

    public void listarPorId(int id) {
        try {
            List<Escola> listar = escolaRepository.listar();
            listar.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    //CREATED
    public void salvar(Escola escolaNova) {
        try {
            Escola escolaAdicionada = escolaRepository.adicionar(escolaNova);
            System.out.println("Escola adicinada com sucesso! " + escolaAdicionada);
        } catch (BancoDeDadosException e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
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



    //UPDATED
    public void atualizar(int id, Escola escola) {
        try {
            boolean editado = escolaRepository.editar(id, escola);
            System.out.println("Escola editada? " + editado + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
    //Remove
    public void deletar(Integer id) {
        try {
            boolean deletado = escolaRepository.remover(id);
            System.out.println("Escola deletada? " + deletado + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}

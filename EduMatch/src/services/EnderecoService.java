package services;

import entities.Endereco;
import exceptions.BancoDeDadosException;
import repository.EnderecoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class EnderecoService {

    private EnderecoRepository enderecoRepository;

    public EnderecoService(){
        enderecoRepository = new EnderecoRepository();
    }

    public void salvarEndereco(Endereco enderecoNovo) {
        try {
            if (enderecoNovo.getCep().length() != 9) {
                throw new Exception("CEP Invalido!");
            }
            Endereco enderecoAdicionado = enderecoRepository.adicionar(enderecoNovo);
            System.out.println("Endereço adicinado com sucesso! " + enderecoAdicionado);
        } catch (BancoDeDadosException e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void listarTodosEnderecos() {
        try {
            List<Endereco> listar = enderecoRepository.listar();
            listar.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void atualizarEndereco(Integer id, Endereco endereco) {
        try {
            boolean editado = enderecoRepository.editar(id, endereco);
            System.out.println("Endereço editado? " + editado + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void deletarEndereco(Integer id) {
        try {
            boolean deletado = enderecoRepository.remover(id);
            System.out.println("Endereço deletado? " + deletado + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}
package services;

import entities.Endereco;
import entities.Escola;
import interfaces.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class EnderecoService implements Service<Endereco> {

    private AtomicInteger COUNTER = new AtomicInteger();
    private static ArrayList<Endereco> enderecos = new ArrayList<>();

    public EnderecoService(){
        inicializarLista();
    }

    private void inicializarLista(){
        enderecos.add(new Endereco(COUNTER.incrementAndGet(), "Rua Presidente Vargas", 123, "Chácara", "12345-678", "Porto Alegre", "RS", "Brasil"));
        enderecos.add(new Endereco(COUNTER.incrementAndGet(), "Rua São Pedro", 456, "Bloco a", "23456-789", "Rio de Janeiro", "RJ", "Brasil"));
        enderecos.add(new Endereco(COUNTER.incrementAndGet(), "Avenida Brasil", 789, "Apto 4", "34567-890", "Caxias Do Sul", "RS", "Brasil"));
        enderecos.add(new Endereco(COUNTER.incrementAndGet(), "Rua Sete de Setembro", 101, "Casa da frente", "45678-901", "Torres", "RS", "Brasil"));
        enderecos.add(new Endereco(COUNTER.incrementAndGet(), "Rua Amazonas", 112, "Casa 3", "56789-012", "Novo Hamburgo", "RS", "Brasil"));
        enderecos.add(new Endereco(COUNTER.incrementAndGet(), "Rua Cabo Frio", 223, "Fundos", "67890-123", "São Paulo", "SP", "Brasil"));
        enderecos.add(new Endereco(COUNTER.incrementAndGet(), "Rua Parana", 334, "Casa A2", "78901-234", "Feira de Santana", "BA", "Brasil"));
        enderecos.add(new Endereco(COUNTER.incrementAndGet(), "Rua São Jorge", 445, "Beco", "89012-345", "Salvador", "BA", "Brasil"));
        enderecos.add(new Endereco(COUNTER.incrementAndGet(), "Avenida Assis Brasil", 556, "Apto 10", "90123-456", "Ilhéus", "BA", "Brasil"));
    }
    @Override
    public boolean salvar(Endereco enderecoNovo){
        for(Endereco endereco : enderecos){
            if(enderecoNovo.equals(endereco)){
                System.out.printf("Endereço já cadastrado!\n");
                return false;
            }
        }
        enderecoNovo.setId(COUNTER.incrementAndGet());
        System.out.printf("Endereço cadastrado com sucesso!\n");
        return enderecos.add(enderecoNovo);
    }
    @Override
    public void listarTodos(){
        for (Endereco endereco : enderecos){
            System.out.println(endereco.toString());
        }
    }

    public Endereco listarPorId(int id) {
        for(Endereco endereco : enderecos){
            if (endereco.getId() == id){
                return endereco;
            }
        } throw new NoSuchElementException("Endereço com o id " + id + " não encontrada.");
    }

    @Override
    public boolean atualizar (int id, Endereco endereco){
        for (Endereco enderecoAtualizar : enderecos) {
            if (enderecoAtualizar.getId() == id) {
                enderecoAtualizar.setCep(endereco.getCep());
                enderecoAtualizar.setCidade(endereco.getCidade());
                enderecoAtualizar.setComplemento(endereco.getComplemento());
                enderecoAtualizar.setEstado(endereco.getEstado());
                enderecoAtualizar.setLogradouro(endereco.getLogradouro());
                enderecoAtualizar.setPais(endereco.getPais());
                enderecoAtualizar.setNumero(endereco.getNumero());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletar(Endereco endereco){
        return enderecos.remove(endereco);
    }
}
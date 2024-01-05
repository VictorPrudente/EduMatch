package services;

import entities.Endereco;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class EnderecoService {

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

    public boolean salvar(Endereco enderecoNovo){
        return enderecos.add(enderecoNovo);
    }

    public void listarTodos(){
        for (Endereco endereco : enderecos){
            System.out.println(endereco.toString());
        }
    }

    public void atualizar (int id, String logradouro, Integer numero, String complemento, String cep, String cidade, String estado, String pais){
        Optional<Endereco> endereco = enderecos.stream().filter(endereco1 -> endereco1.getId() == id).findFirst();

        endereco.ifPresent(endereco1 -> {
            endereco1.setLogradouro(logradouro);
            endereco1.setNumero(numero);
            endereco1.setComplemento(complemento);
            endereco1.setCep(cep);
            endereco1.setCidade(cidade);
            endereco1.setEstado(estado);
            endereco1.setPais(pais);});
        System.out.println("Endereço atualizado com sucesso!");
    }

    public void deletar(int id, Endereco endereco){
        for(Endereco enderecoDeletar : enderecos){
            if(enderecoDeletar.getId() == id){
                enderecos.remove(enderecoDeletar);
            }
        }
    }
}
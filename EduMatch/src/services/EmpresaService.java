package services;
import entities.Empresa;
import entities.Endereco;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class EmpresaService {

    private AtomicInteger COUNTER = new AtomicInteger();
    Integer id = COUNTER.incrementAndGet();
    Random random = new Random();
    private ArrayList<Empresa> empresas = new ArrayList<>();

    public EmpresaService() {
        inicializarLista();
    }

    private void inicializarLista(){

        empresas.add(new Empresa("Pedro", "Brown", "123123", "Alimentício",  id));
        empresas.add(new Empresa( "Lucio", "Blue", "123", "Alimentício", id));
        empresas.add(new Empresa( "Marcos", "Yellow", "123","Alimentício", id));
        empresas.add(new Empresa( "Lucia", "White", "123","Alimentício", id));
        empresas.add(new Empresa( "Maria", "Black", "123","Alimentício", id));
        empresas.add(new Empresa( "Yolanda", "Red", "123","Alimentício", id));
        empresas.add(new Empresa( "Josefina", "Purple", "123","Alimentício", id));
        empresas.add(new Empresa( "Carlas", "Gold", "123","Alimentício", id));
        empresas.add(new Empresa( "Leslie", "Pink", "123","Alimentício", id));

    }

    // CREATE
    public boolean salvar (Empresa empresa){
        for (Empresa empresaNaLista : empresas) {
            if (empresaNaLista.getCnpj().equals(empresa.getCnpj())) {
                System.out.println("O CNPJ deve ser único");
                return false;
            }
        }
        empresas.add(empresa);
        return true;
    }

    // READ
    public ArrayList<Empresa> listarTodos(){
        return empresas;
    }

    public Empresa listarPorId(int Id){
        for (Empresa empresa : empresas){
            if (empresa.getId() == Id){
                return empresa;
            }
        }
        throw new NoSuchElementException("Empresa com o ID " + Id + "não encontrado.");
    }

    // UPDATE
    public void atualizar (int Id, Empresa empresaAtualizada){
        Empresa empresa = empresas.get(Id);
        empresa.setNome(empresaAtualizada.getNome());
        empresa.setSetor(empresaAtualizada.getSetor());
        empresa.setAreaDeAtuacao(empresaAtualizada.getAreaDeAtuacao());
        empresa.setEndereco(empresaAtualizada.getEndereco());
    }

    // DELETE
    public void deletar (int Id, Empresa empresa){
        for (Empresa empresaADeletar : empresas){
            if (empresaADeletar.getId() == Id){
                empresas.remove(empresaADeletar);
            } else {
                throw new NoSuchElementException("Empresa com o ID " + Id + "não encontrado.");
            }
        }
    }
}

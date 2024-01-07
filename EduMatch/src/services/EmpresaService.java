package services;
import entities.Contato;
import entities.Empresa;
import entities.Endereco;
import entities.Usuario;
import interfaces.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class EmpresaService implements Service<Empresa> {

    private AtomicInteger COUNTER = new AtomicInteger();
    Integer id = COUNTER.incrementAndGet();
    Random random = new Random();
    private ArrayList<Empresa> empresas = new ArrayList<>();

    public EmpresaService() {
        inicializarLista();
    }

    private void inicializarLista() {

        empresas.add(new Empresa("Pedro", "Brown", "123123", "Alimentício", id));
        empresas.add(new Empresa("Lucio", "Blue", "123", "Alimentício", id));
        empresas.add(new Empresa("Marcos", "Yellow", "123", "Alimentício", id));
        empresas.add(new Empresa("Lucia", "White", "123", "Alimentício", id));
        empresas.add(new Empresa("Maria", "Black", "123", "Alimentício", id));
        empresas.add(new Empresa("Yolanda", "Red", "123", "Alimentício", id));
        empresas.add(new Empresa("Josefina", "Purple", "123", "Alimentício", id));
        empresas.add(new Empresa("Carlas", "Gold", "123", "Alimentício", id));
        empresas.add(new Empresa("Leslie", "Pink", "123", "Alimentício", id));

    }

    // CREATE
    @Override
    public boolean salvar(Empresa empresa) {
        for (Empresa empresaNaLista : empresas) {
            if (empresaNaLista.getCnpj().equals(empresa.getCnpj())) {
                System.out.println("O CNPJ deve ser único");
                return false;
            }
        }
        empresas.add(empresa);
        System.out.println("Empresa salva com sucesso!");
        return true;
    }

    // READ
    @Override
    public void listarTodos() {
        for (Empresa empresa : empresas) {
            System.out.println(empresa.toString());
        }
    }

    public Empresa listarPorId(int Id) throws Exception {
        for (Empresa empresa : empresas) {
            if (empresa.getId() == Id) {
                return empresa;
            }
        }
        throw new Exception("Empresa com o ID " + Id + "não encontrado.");
    }

    public Empresa listarPorCNPJ(String CNPJ) throws Exception {
        for (Empresa empresa : empresas) {
            if (empresa.getCnpj().equals(CNPJ)) {
                return empresa;
            }
        }
        throw new Exception("A empresa com o CNPJ " + CNPJ + " não encontrado.");
    }

    // UPDATE
    @Override
    public boolean atualizar(int id, Empresa empresa) {

        for (Empresa empresaAtualizar : empresas) {
            if (empresaAtualizar.getId() == id) {
                empresaAtualizar.setAreaDeAtuacao(empresa.getAreaDeAtuacao());
                empresaAtualizar.setSetor(empresa.getSetor());
                empresaAtualizar.setNome(empresa.getNome());
                empresaAtualizar.setEndereco(empresa.getEndereco());
                return true;
            }
        }
        return false;

    }

    // DELETE
    @Override
    public boolean deletar(Empresa empresa) {
        return this.empresas.remove(empresa);
    }
}



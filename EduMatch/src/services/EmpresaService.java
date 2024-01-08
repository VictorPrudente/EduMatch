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

        empresas.add(new Empresa("TechSol","Tecnologia","123456789000101","Desenvolvimento de software e soluções tecnológicas", id));
        empresas.add(new Empresa("EcoBio","Meio Ambiente","987654321000102","Reciclagem e preservação ambiental", id));
        empresas.add(new Empresa("SaúdeVital","Saúde","789012345000103","Serviços de saúde e cuidados médicos", id));
        empresas.add(new Empresa("AgroTech","Agricultura","345678901000104","Desenvolvimento de tecnologias para a agricultura", id));
        empresas.add(new Empresa("ConstruPrime","Construção","012345678000105","Construção civil e infraestrutura", id));
        empresas.add(new Empresa("ModaStyle","Moda","567890123000106","Comércio de roupas e acessórios de moda", id));
        empresas.add(new Empresa("EducaTech","Educação","234567890000107","Tecnologias educacionais e cursos online", id));
        empresas.add(new Empresa("EnergiSolar","Energia","890123456000108","Energia solar e soluções sustentáveis", id));
        empresas.add(new Empresa("TransLog","Logística","456789012000109","Serviços de logística e transporte", id));

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



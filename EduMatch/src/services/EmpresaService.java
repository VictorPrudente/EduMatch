package services;
import entities.Empresa;
import repository.EmpresaRepository;
import exceptions.BancoDeDadosException;
import java.util.*;


public class EmpresaService {
    private EmpresaRepository empresaRepository;

    // criação de um objeto
    public void adicionarEmpresa(Empresa empresa) {
        try {

            if (empresa.getCnpj().length() != 14) {
                throw new Exception("CNPJ Invalido!");
            }

            Empresa empresaAdicionada = empresaRepository.adicionar(empresa);
            System.out.println("empresa adicinada com sucesso! " + empresaAdicionada);
        } catch (BancoDeDadosException e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // remoção
    public void removerEmpresa(Integer id) {
        try {
            boolean conseguiuRemover = empresaRepository.remover(id);
            System.out.println("empresa removida? " + conseguiuRemover + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // atualização de um objeto
    public void editarEmpresa(Integer id, Empresa empresa) {
        try {
            boolean conseguiuEditar = empresaRepository.editar(id, empresa);
            System.out.println("empresa editada? " + conseguiuEditar + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // leitura
    public void listarEmpresas() {
        try {
            List<Empresa> listar = empresaRepository.listar();
            listar.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}



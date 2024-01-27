package VS13.Squad09.EduMatch.services;


import VS13.Squad09.EduMatch.entities.Empresa;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.repositories.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;


    public boolean salvar(Empresa empresa) {
        try {
            if (empresa.getCnpj().length() != 14) {
                throw new Exception("CNPJ Invalido!");
            }

            Empresa empresaAdicionada = empresaRepository.adicionar(empresa);
            System.out.println("empresa adicinada com sucesso! " + empresaAdicionada);
            return true;
        } catch (BancoDeDadosException e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Empresa não cadastrado. Tente novamente.");
        return false;
    }


    public boolean deletar(int id) {
        try {
            empresaRepository.remover(id);
            System.out.printf("Empresa com o ID %d removida com sucesso.", id);
            return true;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        System.out.printf("Empresa com o ID %d não foi removida.");
        return false;
    }

    public boolean atualizar(int id, Empresa empresa) {
        try {
            empresaRepository.editar(id, empresa);
            System.out.printf("Empresa com o ID %d atualizada.", id);
            return true;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        System.out.println("Empresa não atualizada.");
        return false;
    }


    public void listarTodos() {
        try {
            List<Empresa> listar = empresaRepository.listar();
            listar.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}



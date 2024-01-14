package services;

import entities.Certificado;
import entities.Usuario;
import exceptions.BancoDeDadosException;
import interfaces.Service;
import repository.CertificadoRepository;

import java.util.List;

public class CertificadoService implements Service<Certificado> {

    private CertificadoRepository certificadoRepository;

    public CertificadoService() {
        certificadoRepository = new CertificadoRepository();
    }

    @Override
    public boolean salvar(Certificado certificado) {
        try {
            Certificado cert = certificadoRepository.adicionar(certificado);
            System.out.println("Certificado cadastrado com sucesso: " + cert);
            return true;
        } catch (BancoDeDadosException e) {
            System.out.println("ERRO: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        System.out.println("Certificado não cadastrado. Tente novamente");
        return false;
    }

    @Override
    public boolean atualizar(int id, Certificado certificado) {
        System.out.println("Não é possível atualizar o certificado após a emissão.");
        return false;
    }

    @Override
    public boolean deletar(Certificado certificado) {
        int id = certificado.getId();
        try {
            certificadoRepository.remover(id);
            System.out.printf("Certificado com o id %d removido com sucesso.");
            return true;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        System.out.println("Certificado não removido.");
        return false;
    }

    @Override
    public void listarTodos() {
        try {
            List<Certificado> certificados = certificadoRepository.listar();
            certificados.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public Certificado listarUltimo(Usuario usuario) {
        int id = usuario.getId();
        try {
            Certificado certificado = certificadoRepository.listarUltimo(id);
            return certificado;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        System.out.println("Nenhum certificado encontrado. Jogue para adquirir seu primeiro!");
        return null;
    }
}

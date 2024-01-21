package utils;

import entities.Certificado;
import entities.Usuario;
import services.CertificadoService;

import java.util.Scanner;

public class CertificadoController {

    private static CertificadoService certificadoService = new CertificadoService();

    public CertificadoController(){
        CertificadoService certificadoService = new CertificadoService();
    }

    public void certificadoOpcao(int opcao, Usuario usuario) {
        switch (opcao) {
            case 1: {
                System.out.println("\nCertificados adquiridos: \n");
                certificadoService.listarPorUsuario(usuario);
                break;
            }
            case 2: {
                System.out.println("\nUltimo certificado adquirido:");
                System.out.println(certificadoService.listarUltimo(usuario));
                break;
            }
            case 3: {
                System.out.println("\nVoltando ao menu.");
                break;
            }
            default: {
                System.out.println("\n\u001B[31mOpção Inválida. Retornando ao menu principal.\u001B[0m\n");
            }
        }
    }
}
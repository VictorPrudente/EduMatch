package services;

import entities.Certificado;
import entities.Usuario;
import exceptions.BancoDeDadosException;
import repository.CertificadoRepository;

public class CertificadoService {
    public void validarCertificado(Usuario usuario) {
        try {
            if (usuario.getPontuacao() != 14) {
                throw new Exception("Não há pontuação para emitir certificado!");
            }
            if (usuario.getCertificados() != null){
                throw new Exception("Você já possui um certificado nessa área!");
            }

            usuario usuario = CertificadoRepository.
        } catch (BancoDeDadosException e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

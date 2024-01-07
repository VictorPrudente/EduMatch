package interfaces;

import entities.Usuario;

public interface Certificado {
    boolean validaCertificado(Usuario usuario);
    void emitiDataConclusao();
}

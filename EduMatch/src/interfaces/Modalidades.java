package interfaces;

import entities.Usuario;

public interface Modalidades {

    void comecarJogo();

    void ganharJogo();

    void ganharJogoSemErros();

    void desistir();

    void perderJogo();

    Certificado emitirCertificado(Usuario usuario);
}

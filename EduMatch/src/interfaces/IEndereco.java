package interfaces;

public interface IEndereco {
    String getLogradouro();
    void setLogradouro();

    int getNumero();
    void setNumero();

    String getComplemento();
    void setComplemento();

    String getCep();
    void setCep();

    String getCidade();
    void setCidade();

    String getEstado();
    void setEstado();

    String getPais();
    void setPais();

    String imprimirEndereco();
}

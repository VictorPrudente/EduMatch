package entities;

public class Endereco {
    private String id;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;

    public Endereco(String id, String logradouro, Integer numero, String complemento, String cep, String cidade, String estado, String pais){
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "Logradouro: " + logradouro + ", " + numero +
                "\nComplemento: " + complemento +
                "\nCEP: " + cep +
                "\nCidade: " + cidade +
                "\nEstado: " + estado +
                "\nPaís: " + pais;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogradouro(){
        return logradouro;
    }

    public void setLogradouro(){
        this.logradouro = logradouro;
    }

    public Integer getNumero(){
        return numero;
    }

    public void setNumero(){
        this.numero = numero;
    }

    public String getComplemento(){
        return complemento;
    }

    public void setComplemento(){
        this.complemento = complemento;
    }

    public String getCep(){
        return cep;
    }

    public void setCep(){
        this.cep = cep;
    }

    public String getCidade(){
        return cidade;
    }

    public void setCidade(){
        this.cidade = cidade;
    }

    public String getEstado(){
        return estado;
    }

    public void setEstado(){
        this.estado = estado;
    }

    public String getPais(){
        return pais;
    }
    public void setPais(){
        this.pais = pais;
    }
}

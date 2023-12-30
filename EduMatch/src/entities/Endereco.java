package entities;

public class Endereco {
    String logradouro;
    Integer numero;
    String complemento;
    String cep;
    String cidade;
    String estado;
    String pais;

    public Endereco(String logradouro, Integer numero, String complemento, String cep, String cidade, String estado, String pais){
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

    public String imprimirEndereco(){
        return "Logradouro: " + logradouro + ", " + numero +
                "\nComplemento: " + complemento +
                "\nCEP: " + cep +
                "\nCidade: " + cidade +
                "\nEstado: " + estado +
                "\nPaís: " + pais;
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

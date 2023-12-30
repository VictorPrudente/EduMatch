package entities;
import interfaces.IEndereco;

public class Endereco implements IEndereco {
    String logradouro;
    int numero;
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

    @Override
    public String imprimirEndereco(){
        return "Logradouro: " + logradouro + ", " + numero +
                "\nComplemento: " + complemento +
                "\nCEP: " + cep +
                "\nCidade: " + cidade +
                "\nEstado: " + estado +
                "\nPaís: " + pais;
    }

    @Override
    public String getLogradouro(){
        return logradouro;
    }

    @Override
    public void setLogradouro(){
        this.logradouro = logradouro;
    }

    @Override
    public int getNumero(){
        return numero;
    }

    @Override
    public void setNumero(){
        this.numero = numero;
    }

    @Override
    public String getComplemento(){
        return complemento;
    }

    @Override
    public void setComplemento(){
        this.complemento = complemento;
    }

    @Override
    public String getCep(){
        return cep;
    }

    @Override
    public void setCep(){
        this.cep = cep;
    }

    @Override
    public String getCidade(){
        return cidade;
    }

    @Override
    public void setCidade(){
        this.cidade = cidade;
    }

    @Override
    public String getEstado(){
        return estado;
    }

    @Override
    public void setEstado(){
        this.estado = estado;
    }

    @Override
    public String getPais(){
        return pais;
    }

    @Override
    public void setPais(){
        this.pais = pais;
    }
}

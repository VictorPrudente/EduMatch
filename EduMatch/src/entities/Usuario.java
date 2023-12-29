package entities;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    String login;
    String senha;
    Integer tipoDeUser;
    String CPF;
    Boolean AutorizacaoDosPais;
    Integer idade;
    Endereco endereco;
    Integer pontuacao;
    
    public void aumentarPontuacao(int pontos){
        this.pontuacao += pontos;
    }

}

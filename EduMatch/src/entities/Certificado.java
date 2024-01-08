package entities;

import entities.enums.Games;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Certificado {

    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private Games trilha;

    private LocalDateTime conclusao = LocalDateTime.now();

    private Usuario usuario;

    public Certificado() {
    }

    public Certificado(Games trilha, LocalDateTime conclusao, Usuario usuario) {
        this.trilha = trilha;
        this.conclusao = conclusao;
        this.usuario = usuario;
    }

    public Games getGame() {
        return trilha;
    }

    public void setGame(Games game) {
        this.trilha = game;
    }

    public LocalDateTime getConclusao() {
        return conclusao;
    }

    public void setConclusao(LocalDateTime conclusao) {
        this.conclusao = conclusao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return String.format("""
                Reconhecemos que o(a) usuário(a): %s %s 
                Com o CPF: %s
                Adquiriu o certificado no dia e hora %s
                Da trilha %s""", usuario.getNome(), usuario.getSobrenome(), usuario.getCPF(), dtf.format(conclusao), trilha.name());
    }
}

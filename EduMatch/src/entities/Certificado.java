package entities;

import entities.enums.Games;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Certificado {

    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private Games trilha;

    private LocalDateTime conclusao = LocalDateTime.now();

    private Usuario usuario;

    private Integer id;

    public Certificado() {
    }

    public Certificado(Games trilha, LocalDateTime conclusao, Usuario usuario, Integer id) {
        this.trilha = trilha;
        this.conclusao = conclusao;
        this.usuario = usuario;
        this.id = id;
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

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("""
                Reconhecemos que o(a) usuário(a): %s %s 
                Com o CPF: %s
                e ID: %s
                Adquiriu o certificado no dia e hora %s
                Da trilha %s""", usuario.getNome(), usuario.getSobrenome(), usuario.getCPF(), usuario.getId(), dtf.format(conclusao), trilha.name().replace("_", " "));
    }

}

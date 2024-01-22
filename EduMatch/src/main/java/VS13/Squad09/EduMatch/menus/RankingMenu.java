package VS13.Squad9.EduMatch.menu;

import entities.Usuario;
import services.UsuarioService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RankingMenu {

    UsuarioService usuarioService = new UsuarioService();

    public void rankingOpcoes(int opcao, Usuario usuario) {

        switch (opcao) {
            case 1: {
                System.out.println("\nSua pontuação é de: " + usuario.getPontuacao() + "\n");
                break;
            }
            case 2: {
                List<Usuario> usuarios = usuarioService.rankearUsuarios();
                Map<Integer, Usuario> ranking = usuarios.stream().collect(Collectors.toMap(usuarios::indexOf, user -> user));
                ranking.forEach((i, jogador) -> System.out.printf("%n%d° Posicao:%n%s %n", ++i, jogador));
                break;
            }
            default: {
                System.out.println("\nRetornando ao Menu Principal");
                break;
            }
        }
    }
}

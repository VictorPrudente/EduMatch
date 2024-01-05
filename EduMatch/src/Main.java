import entities.Usuario;
import services.UsuarioService;

public class Main {
    public static void main(String[] args) {

        UsuarioService usuarioService = new UsuarioService();

        Usuario usuario = new Usuario();
        usuarioService.salvar(usuario);

    }
}
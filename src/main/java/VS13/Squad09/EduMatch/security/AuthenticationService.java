package VS13.Squad09.EduMatch.security;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService{
    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> loginOptional = usuarioService.findByEmail(username);
        return loginOptional
                .orElseThrow(() -> new UsernameNotFoundException("Login inválido"));
    }
}

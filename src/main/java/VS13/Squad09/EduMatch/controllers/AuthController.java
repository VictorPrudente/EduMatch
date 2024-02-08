package VS13.Squad09.EduMatch.controllers;
import VS13.Squad09.EduMatch.dtos.LoginDTO;
import VS13.Squad09.EduMatch.dtos.request.UsuarioCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.exceptions.RegraDeNegocioException;
import VS13.Squad09.EduMatch.security.TokenService;
import VS13.Squad09.EduMatch.services.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name="Login", description = "Endpoint de Login")
@Validated
@RequiredArgsConstructor
public class AuthController {
    private final TokenService tokenService;
    public final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public String auth(@RequestBody @Valid LoginDTO LoginDTO) throws RegraDeNegocioException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        LoginDTO.getEmail(),
                        LoginDTO.getSenha()
                );

        Authentication authentication =
                authenticationManager.authenticate(
                        usernamePasswordAuthenticationToken);

        Usuario usuarioValidado = (Usuario) authentication.getPrincipal();

        return tokenService.generateToken(usuarioValidado);
    }

    @PostMapping("/create-usuario")
    public UsuarioDTO create(@RequestBody @Valid UsuarioCreateDTO usuarioCreateDTO) throws RegraDeNegocioException {
        return usuarioService.criar(usuarioCreateDTO);
    }

    @GetMapping("/usuario-logado")
    public ResponseEntity<Usuario> findLoggedUser() throws RegraDeNegocioException {
        return ResponseEntity.ok(usuarioService.getLoggedUser());
    }
}

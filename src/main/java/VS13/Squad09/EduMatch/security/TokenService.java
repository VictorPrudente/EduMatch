package VS13.Squad09.EduMatch.security;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.services.UsuarioService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {
    static final String HEADER_STRING = "Authorization";

    private static final String TOKEN_PREFIX = "Bearer";

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;
    private final UsuarioService usuarioService;

    public String generateToken(Usuario usuario) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + Long.parseLong(expiration));

        return TOKEN_PREFIX + " " +
                Jwts.builder()
                        .setIssuer("pessoa-api")
                        .claim(Claims.ID, usuario.getIdUsuario().toString())
                        .setIssuedAt(now)
                        .setExpiration(exp)
                        .signWith(SignatureAlgorithm.HS256, secret)
                        .compact();
    }

    public Optional<Usuario> isValid(String token) {
        if (token != null) {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            String user = body.get(Claims.ID, String.class);
            if (user != null) {
                return usuarioService.findById(Integer.valueOf(user));
            }
        }
        return Optional.empty();
    }
}
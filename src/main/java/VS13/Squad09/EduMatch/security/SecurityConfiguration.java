
package VS13.Squad09.EduMatch.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final TokenService tokenService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable().and()
                .cors().and()
                .csrf().disable()
                .authorizeHttpRequests((authz) -> authz
                        .antMatchers("/auth/login", "/", "/auth/create-usuario").permitAll()

                        // USUARIO
                        .antMatchers(HttpMethod.GET, "/usuario/all").hasAnyAuthority("ROLE_COMPANY", "ROLE_ADM")
                        .antMatchers(HttpMethod.GET, "/usuario/id").hasAnyAuthority("ROLE_USUARIO", "ROLE_COMPANY", "ROLE_ADM")
                        .antMatchers(HttpMethod.GET, "/usuario/usuario-paginado").hasAnyAuthority("ROLE_COMPANY", "ROLE_ADM")
                        .antMatchers(HttpMethod.GET, "/usuario/usuario-completo/**").hasAnyAuthority("ROLE_COMPANY", "ROLE_ADM", "ROLE_USUARIO")
                        .antMatchers(HttpMethod.GET, "/usuario/usuario-com-certificado/**").hasAnyAuthority("ROLE_COMPANY", "ROLE_ADM", "ROLE_USUARIO")
                        .antMatchers(HttpMethod.DELETE, "/usuario/**").hasAnyAuthority("ROLE_USUARIO", "ROLE_ADM")
                        .antMatchers(HttpMethod.PUT, "/usuario/**").hasAnyAuthority("ROLE_USUARIO", "ROLE_ADM")

                        // CONTATO
                        .antMatchers("/contato/**").hasAnyAuthority("ROLE_USUARIO", "ROLE_ADM", "ROLE_COMPANY")

                        // ENDERECO
                        .antMatchers("/endereco/**").hasAnyAuthority("ROLE_USUARIO", "ROLE_ADM", "ROLE_COMPANY")

                        // CERTIFICADO
                        .antMatchers(HttpMethod.GET, "/certificado/**").hasAnyAuthority("ROLE_USUARIO", "ROLE_ADM")
                        .antMatchers(HttpMethod.POST, "/certificado/**").hasAnyAuthority("ROLE_ADM")
                        .antMatchers(HttpMethod.DELETE, "/certificado/**").hasAnyAuthority("ROLE_ADM")

                        // INSIGNIA
                        .antMatchers(HttpMethod.GET, "/insignia/**").hasAnyAuthority("ROLE_USUARIO", "ROLE_ADM")
                        .antMatchers(HttpMethod.POST, "/insignia/**").hasAnyAuthority("ROLE_ADM")

                        // PROVA
                        .antMatchers("/prova/**").hasAnyAuthority("ROLE_ADM")

                        // EMPRESA
                        .antMatchers("/empresa/**").hasAuthority("ROLE_ADM")

                        // QUESTAO
                        .antMatchers("/questao/**").hasAuthority("ROLE_ADM")

                        // RANKING
                        .antMatchers("/ranking/**").hasAnyAuthority("ROLE_ADM", "ROLE_USUARIO", "ROLE_COMPANY")

                        // ALL
                        .antMatchers("/**").hasAnyAuthority("ROLE_ADM")
                        .anyRequest().authenticated()
                );
        http.addFilterBefore(new TokenAuthenticationFilter(tokenService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/v3/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui/**");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .exposedHeaders("Authorization");
            }
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


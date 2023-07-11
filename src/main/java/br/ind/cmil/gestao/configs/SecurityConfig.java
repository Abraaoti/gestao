package br.ind.cmil.gestao.configs;

import br.ind.cmil.gestao.model.enums.TipoPerfil;
import br.ind.cmil.gestao.model.security.jwt.JwtAuthenticationFilter;
import br.ind.cmil.gestao.model.services.interfaces.IUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author ti
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final IUserService userService;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, IUserService userService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userService = userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    private static final String ADMIN = TipoPerfil.ADMIN.getValue();
    private static final String ADMINISTRATIVO = TipoPerfil.ADMINISTRATIVO.getValue();
    private static final String DIRETOR = TipoPerfil.DIRETOR.getValue();
    private static final String ENGENHEIRO = TipoPerfil.ENGENHEIRO.getValue();
    private static final String COMPRADOR = TipoPerfil.COMPRADOR.getValue();
    private static final String FINANCEIRO = TipoPerfil.FINANCEIRO.getValue();
    private static final String RH = TipoPerfil.RH.getValue();
    private static final String TECNICO = TipoPerfil.TECNICO.getValue();

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers(new AntPathRequestMatcher("/api/free")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/u/confirmacao/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/pessoa/salvar")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/auth/authenticate")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/u/registrar")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/perfil/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/u/editar/senha", "/u/confirmar/senha")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/empresa/avaliar/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                //acessos privados admin                
                .requestMatchers("/api/**").hasAuthority(ADMIN)
                .requestMatchers("/api/administrativo").hasAuthority(ADMINISTRATIVO)
                .requestMatchers("/api/empresa/editar/senha", "/u/confirmar/senha").hasAnyAuthority(FINANCEIRO, ADMINISTRATIVO, ADMIN)
                .requestMatchers("/api/empresa/**").hasAuthority(ADMIN)
                //acessos privados financeiro                
                .requestMatchers("/api/financeiro/**").hasAnyAuthority(ADMINISTRATIVO, ADMIN, FINANCEIRO, DIRETOR)
                .requestMatchers("/api/empresas/**").hasAnyAuthority(ADMINISTRATIVO, ADMIN, FINANCEIRO, DIRETOR)
                .requestMatchers("/api/pagarcontas/**").hasAnyAuthority(ADMINISTRATIVO, ADMIN, FINANCEIRO, DIRETOR)
                .requestMatchers("/api/administrativo/**").hasAnyAuthority(ADMINISTRATIVO, ADMIN)
                //acessos privados diretoria
                .requestMatchers("/api/diretoria/**").hasAuthority(DIRETOR)
                //.requestMatchers("/api/pessoa/**").hasAuthority(RH)
                //acessos privados tecnico
                .requestMatchers("/api/engenheiro/**").hasAuthority(ENGENHEIRO)
                .requestMatchers("/api/financeiro/**").hasAuthority(FINANCEIRO)
                .requestMatchers("/api/tecnico").hasAuthority(TECNICO)
                .requestMatchers("/api/comprador").hasAuthority(COMPRADOR)
                .anyRequest()
                .authenticated()
                )
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.headers(headers -> headers.frameOptions(frameOption -> frameOption.sameOrigin()));

        return http.build();
    }

}

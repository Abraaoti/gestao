package br.ind.cmil.gestao.configs;

import br.ind.cmil.gestao.model.enums.TipoPerfil;
import br.ind.cmil.gestao.model.security.jwt.ApiAuthenticationEntryPoint;
import br.ind.cmil.gestao.model.security.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author ti
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // private final IUserService userService;
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    ApiAuthenticationEntryPoint authenticationEntryPoint;

    private static final String ADMIN = TipoPerfil.ADMIN.getValue();
    private static final String ADMINISTRATIVO = TipoPerfil.ADMINISTRATIVO.getValue();
    private static final String DIRETOR = TipoPerfil.DIRETOR.getValue();
    private static final String ENGENHEIRO = TipoPerfil.ENGENHEIRO.getValue();
    private static final String COMPRADOR = TipoPerfil.COMPRADOR.getValue();
    private static final String FINANCEIRO = TipoPerfil.FINANCEIRO.getValue();
    // private static final String RH = TipoPerfil.RH.getValue();
    private static final String TECNICO = TipoPerfil.TECNICO.getValue();

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint))
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers(new AntPathRequestMatcher("/api/free")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/t/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/u/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/p/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/c/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/e/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/auth/authenticate")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/perfil/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/departamento/**")).permitAll()
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
                //.authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.headers(headers -> headers.frameOptions(frameOption -> frameOption.sameOrigin()));

        return http.build();
    }

    @Bean
    public RegistrationBean jwtAuthFilterRegister(JwtAuthenticationFilter filter) {
        FilterRegistrationBean<JwtAuthenticationFilter> registrationBean = new FilterRegistrationBean<>(filter);
        registrationBean.setEnabled(false);
        return registrationBean;
    }
    /**
     * @Bean public AuthenticationProvider authenticationProvider() {
     * DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
     * authProvider.setUserDetailsService(userService);
     * authProvider.setPasswordEncoder(passwordEncoder()); return authProvider;
     * }*
     */
}

package br.ind.cmil.gestao.configs;

import br.ind.cmil.gestao.model.enums.TipoPerfil;
import br.ind.cmil.gestao.model.security.jwt.ApiAuthenticationEntryPoint;
import br.ind.cmil.gestao.model.security.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;
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
    private static final String ADMINISTRADOR = TipoPerfil.ADMINISTRADOR.getValue();
    private static final String ADMINISTRATIVO = TipoPerfil.ADMINISTRATIVO.getValue();
    private static final String ASSISTENTE = TipoPerfil.ASSISTENTEADMINISTRATIVO.getValue();
    private static final String AUXDMINISTRATIVO = TipoPerfil.AUXDMINISTRATIVO.getValue();
    private static final String DIRETOR = TipoPerfil.DIRETOR.getValue();
    private static final String ENGENHEIRO = TipoPerfil.ENGENHEIRO.getValue();
    private static final String COMPRADOR = TipoPerfil.COMPRADOR.getValue();
    private static final String FINANCEIRO = TipoPerfil.FINANCEIRO.getValue();
    private static final String RH = TipoPerfil.RH.getValue();
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
                .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login-error")
                .permitAll()
                )
                .logout(logout -> logout
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .permitAll()
                )
                .exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint))
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS)
                )
                .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/home")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/t/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/u/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/p/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/c/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/e/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/authenticate")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/perfis/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/departamento/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/u/editar/senha", "/u/confirmar/senha")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/empresa/avaliar/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                .requestMatchers("/u/novo/cadastro", "/u/cadastro/realizado", "/u/cadastro/paciente/salvar").permitAll()
                .requestMatchers("/u/confirmacao/cadastro").permitAll()
                .requestMatchers("/u/p/**").permitAll()
                //acessos privados admin  
                .requestMatchers("/u/editar/senha", "/u/confirmar/senha").hasAnyAuthority(ADMINISTRADOR, AUXDMINISTRATIVO,ASSISTENTE,RH)
                .requestMatchers("/u/**").hasAuthority(ADMIN)
                .requestMatchers("/administrativo/**").hasAuthority(ADMINISTRATIVO)
                //acessos privados auxiliar administrativo                
                .requestMatchers("/administrador/**").hasAuthority(ADMINISTRADOR)
                //acessos privados auxiliar administrativo                
                .requestMatchers("/auxiliar/**").hasAuthority(AUXDMINISTRATIVO)
                //acessos privados auxiliar administrativo                
                .requestMatchers("/assistente/**").hasAuthority(ASSISTENTE)
               
                //acessos privados financeiro                
                .requestMatchers("/rh/**").hasAnyAuthority(ADMIN, RH)
                .requestMatchers("/rh/pessoas/**").hasAnyAuthority(ADMINISTRATIVO, ADMIN, RH, DIRETOR)
                
                //acessos privados diretoria
                .requestMatchers("/diretoria/**").hasAuthority(DIRETOR)
                //.requestMatchers("/api/pessoa/**").hasAuthority(RH)
                //acessos privados tecnico
                .requestMatchers("/engenheiro/**").hasAuthority(ENGENHEIRO)
                .requestMatchers("/financeiro/**").hasAuthority(FINANCEIRO)
                .requestMatchers("/tecnico").hasAuthority(TECNICO)
                .requestMatchers("/comprador").hasAuthority(COMPRADOR)
                .anyRequest()
                .authenticated()
                )
                //.formLogin(formLoginCustomizer-> formLoginCustomizer)
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

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/resources/**", "/js/**", "/css/**", "/webjars/**", "/docs/**", "/image/**");

    }

    @Bean
    public SessionAuthenticationStrategy sessionAuthStrategy() {
        return new RegisterSessionAuthenticationStrategy(sessionRegistry());
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public ServletListenerRegistrationBean<?> servletListenerRegistrationBean() {
        return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
    }

    /**
     * @Bean public AuthenticationProvider authenticationProvider() {
     * DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
     * authProvider.setUserDetailsService(userService);
     * authProvider.setPasswordEncoder(passwordEncoder()); return authProvider;
     * }*
     */
}

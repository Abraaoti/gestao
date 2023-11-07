package br.ind.cmil.gestao.configs;

import br.ind.cmil.gestao.model.enums.TipoPerfil;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
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

    @Autowired
    private CustomizarUsuarioDetailsService userDetailsService;

    private static final String ADMIN = TipoPerfil.ADMIN.getValue();
    private static final String ADMINISTRADOR = TipoPerfil.ADMINISTRADOR.getValue();
    private static final String ASSISTENTE = TipoPerfil.ASSISTENTE.getValue();
    private static final String AUXILIAR = TipoPerfil.AUXILIAR.getValue();
    private static final String COMPRADOR = TipoPerfil.COMPRADOR.getValue();
    private static final String DIRETOR = TipoPerfil.DIRETOR.getValue();
    private static final String ENGENHEIRO = TipoPerfil.ENGENHEIRO.getValue();
    private static final String FUNCIONARIO = TipoPerfil.FUNCIONARIO.getValue();
    private static final String GERENTE = TipoPerfil.GERENTE.getValue();
    private static final String LIDERFINANCEIRO = TipoPerfil.LIDERFINANCEIRO.getValue();
    private static final String TECNICO = TipoPerfil.TECNICO.getValue();
    private static final String USUARIO = TipoPerfil.USUARIO.getValue();

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors(cors -> cors.disable())
                .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/u/cadastrar").permitAll()
                .requestMatchers(HttpMethod.GET, "/u/novo/cadastro").permitAll()
                .requestMatchers(HttpMethod.GET, "/u/cadastro/realizado").permitAll()
                .requestMatchers(HttpMethod.GET, "/u/confirmacao/cadastro").permitAll()
                //.requestMatchers(new AntPathRequestMatcher("/webjars/**", "/css/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/authenticate")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/u/editar/senha", "/u/confirmar/senha")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/empresa/avaliar/**")).permitAll()
                //.requestMatchers(toH2Console()).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/u/novo/cadastro", "/u/cadastro/realizado")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/u/p/**")).permitAll()
                //acessos privados para todos ativos  
                .requestMatchers(new AntPathRequestMatcher("/u/editar/senha/", "/u/confirmar/senha/")).hasAnyAuthority(ADMINISTRADOR, ASSISTENTE, AUXILIAR, COMPRADOR, DIRETOR, ENGENHEIRO, GERENTE, FUNCIONARIO, LIDERFINANCEIRO, TECNICO, USUARIO)
                //acessos privados admin  
                .requestMatchers(new AntPathRequestMatcher("/u/**", "/perfis/**")).hasAuthority(ADMIN)
                //acessos privados  administrador   

                //.requestMatchers("/departamento/**","/administrador**","/projeto/**").hasAuthority(ADMINISTRADOR)
                .requestMatchers(new AntPathRequestMatcher("/administrador/**", "/lotacao/**")).hasRole(ADMINISTRADOR)
                .requestMatchers(new AntPathRequestMatcher("/departamento/**", "/departamento/add")).hasAnyAuthority(ADMINISTRADOR)
                .requestMatchers(new AntPathRequestMatcher("/administrador/dados/", "/administrador/salvar/")).hasAnyAuthority(ADMINISTRADOR, ADMIN)
                .requestMatchers(new AntPathRequestMatcher("/administrador/editar/")).hasAnyAuthority(ADMINISTRADOR, ADMIN)
                .requestMatchers(new AntPathRequestMatcher("/cargo/**","/cargo/add/")).hasAnyAuthority( ADMINISTRADOR)
                .requestMatchers(new AntPathRequestMatcher("/cargo/update/","/cargo/create/")).hasAnyAuthority(ADMIN, ADMINISTRADOR)
                //acessos privados assistente administrativo                
                .requestMatchers(new AntPathRequestMatcher("/assistente/**", "/funcionarios/**")).hasAuthority(ASSISTENTE)
                .requestMatchers(new AntPathRequestMatcher("/funcionarios/add/")).hasAuthority(ASSISTENTE)
                .requestMatchers(new AntPathRequestMatcher("/assistente/dados/", "/assistente/create/")).hasAnyAuthority(ASSISTENTE, ADMIN)
                .requestMatchers(new AntPathRequestMatcher("/assistente/update/")).hasAnyAuthority(ASSISTENTE, ADMIN)
                .requestMatchers(new AntPathRequestMatcher("/funcionarios/salvar/", "/funcionarios/editar/")).hasAnyAuthority(ASSISTENTE)
                //acessos privados auxiliar administrativo                
                .requestMatchers(new AntPathRequestMatcher("/auxiliar/**", "/presenca/**")).hasAuthority(AUXILIAR)
                .requestMatchers(new AntPathRequestMatcher("/auxiliar/dados/", "/auxiliar/create/")).hasAnyAuthority(AUXILIAR, ADMIN)
                .requestMatchers(new AntPathRequestMatcher("/auxiliar/update/")).hasAnyAuthority(AUXILIAR, ADMIN)
                // .requestMatchers("/auxiliar/dados", "/auxiliar/create", "/auxiliar/update").hasAnyAuthority(AUXILIAR, ADMIN)
                .requestMatchers(new AntPathRequestMatcher("/diretoria/**")).hasAuthority(DIRETOR)
                .requestMatchers(new AntPathRequestMatcher("/diretoria/dados/")).hasAnyAuthority(DIRETOR, ADMIN)
                .requestMatchers(new AntPathRequestMatcher("/diretoria/create/")).hasAnyAuthority(DIRETOR, ADMIN)
                .requestMatchers(new AntPathRequestMatcher("/departamento/lista/")).hasAnyAuthority(ASSISTENTE)
                .requestMatchers(new AntPathRequestMatcher("/cargo/lista/")).hasAnyAuthority(ADMINISTRADOR, ASSISTENTE)
                .requestMatchers(new AntPathRequestMatcher("/perfis/lista/")).hasAnyAuthority(ADMINISTRADOR, ADMIN)
                .requestMatchers(new AntPathRequestMatcher("/funcionario/lista/")).hasAnyAuthority(ADMIN, ASSISTENTE, ADMINISTRADOR)
                .requestMatchers(new AntPathRequestMatcher("/suprimento/**")).hasAnyAuthority(ADMIN, ADMINISTRADOR, COMPRADOR)
                .requestMatchers(new AntPathRequestMatcher("/engenheiro/**")).hasAuthority(ENGENHEIRO)
                .requestMatchers(new AntPathRequestMatcher("/contas/**")).hasAuthority(LIDERFINANCEIRO)
                .requestMatchers(new AntPathRequestMatcher("/tecnicos/**")).hasAuthority(TECNICO)
                .requestMatchers(new AntPathRequestMatcher("/comprador")).hasAuthority(COMPRADOR)
                .anyRequest()
                .authenticated()
                )
                .csrf((csrf) -> csrf
                .ignoringRequestMatchers(toH2Console())
                .ignoringRequestMatchers("/no-csrf")
                .disable()
                )
                .formLogin((form) -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login-error")
                .permitAll()
                )
                .logout((logout) -> logout
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                )
                .exceptionHandling((ex) -> ex
                .accessDeniedPage("/negado")
                )
                .headers(headers -> headers.frameOptions(FrameOptionsConfig::disable));

        http.sessionManagement((session) -> session
                .maximumSessions(1)
                .expiredUrl("/expired")
                .sessionRegistry(sessionRegistry())
        );
        http.sessionManagement((session) -> session
                .sessionFixation()
                .newSession()
                .sessionAuthenticationStrategy(sessionAuthStrategy())
        );
        //http.authenticationProvider(authenticationProvider());

        // http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        // http.headers(headers -> headers.frameOptions(frameOption -> frameOption.sameOrigin()));
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/resources/**", "/h2-console/**", "/js/**", "/css/**", "/webjars/**", "/docs/**", "/image/**");

    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
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

}

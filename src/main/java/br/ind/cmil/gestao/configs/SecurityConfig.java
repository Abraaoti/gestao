package br.ind.cmil.gestao.configs;


import br.ind.cmil.gestao.perfil.enums.TipoPerfil;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
//@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    private static final String ADMIN = TipoPerfil.ROLE_ADMIN.getValue();
    private static final String ADMINISTRATIVO = TipoPerfil.ROLE_ADMINISTRATIVO.getValue();
    private static final String ASSISTENTE = TipoPerfil.ROLE_ASSISTENTE.getValue();
    private static final String AUXILIAR = TipoPerfil.ROLE_AUXILIAR.getValue();
    private static final String COMPRADOR = TipoPerfil.ROLE_COMPRADOR.getValue();
    private static final String DIRETOR = TipoPerfil.ROLE_DIRETOR.getValue();
    private static final String ENGENHEIRO = TipoPerfil.ROLE_ENGENHEIRO.getValue();
    private static final String RH = TipoPerfil.ROLE_RH.getValue();
    private static final String GERENTE = TipoPerfil.ROLE_GERENTE.getValue();
    private static final String TECNICO = TipoPerfil.ROLE_TECNICO.getValue();
    private static final String USUARIO = TipoPerfil.ROLE_USUARIO.getValue();

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
                .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                .requestMatchers(HttpMethod.POST, "/login", "/u/cadastrar").permitAll()
                .requestMatchers(HttpMethod.GET, "/u/novo/cadastro", "/u/cadastro/realizado", "/u/confirmacao/cadastro").permitAll()
                //.requestMatchers(new AntPathRequestMatcher("/webjars/**", "/css/**")).permitAll()
                .requestMatchers(HttpMethod.GET,"/h2-console/**").permitAll()
                .requestMatchers(new AntPathRequestMatcher("/authenticate")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/u/editar/senha", "/u/confirmar/senha")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/empresa/avaliar/**")).permitAll()
                //.requestMatchers(toH2Console()).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/u/novo/cadastro", "/u/cadastro/realizado")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/u/p/**")).permitAll()
                //acessos privados para todos ativos  
                .requestMatchers(new AntPathRequestMatcher("/u/editar/senha/", "/u/confirmar/senha/")).hasAnyAuthority(ADMINISTRATIVO, ASSISTENTE, AUXILIAR, COMPRADOR, DIRETOR, ENGENHEIRO, GERENTE, RH, TECNICO, USUARIO)
                //acessos privados admin  
                .requestMatchers(new AntPathRequestMatcher("/u/**", "/perfis/**")).hasAuthority(ADMIN)
                //acessos privados  administrador   

                //.requestMatchers("/departamento/**","/administrador**","/projeto/**").hasAuthority(ADMINISTRADOR)
                .requestMatchers(new AntPathRequestMatcher("/administrador/**", "/centroCusto/**")).hasRole(ADMINISTRATIVO)
                .requestMatchers(new AntPathRequestMatcher("/departamento/**", "/departamento/add")).hasAnyAuthority(ADMINISTRATIVO)
                .requestMatchers(new AntPathRequestMatcher("/administrador/dados/", "/administrador/salvar/")).hasAnyAuthority(ADMINISTRATIVO, ADMIN)
                .requestMatchers(new AntPathRequestMatcher("/administrador/editar/")).hasAnyAuthority(ADMINISTRATIVO, ADMIN)
                .requestMatchers(new AntPathRequestMatcher("/cargo/**","/cargo/add/")).hasAnyAuthority( ADMINISTRATIVO)
                .requestMatchers(new AntPathRequestMatcher("/cargo/update/","/cargo/create/")).hasAnyAuthority(ADMIN, ADMINISTRATIVO)
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
                .requestMatchers(new AntPathRequestMatcher("/cargo/lista/")).hasAnyAuthority(ADMINISTRATIVO, ASSISTENTE)
                .requestMatchers(new AntPathRequestMatcher("/perfis/lista/")).hasAnyAuthority(ADMINISTRATIVO, ADMIN)
                .requestMatchers(new AntPathRequestMatcher("/funcionario/lista/")).hasAnyAuthority(ADMIN, ASSISTENTE, ADMINISTRATIVO)
                .requestMatchers(new AntPathRequestMatcher("/suprimento/**")).hasAnyAuthority(ADMIN, ADMINISTRATIVO, COMPRADOR)
                .requestMatchers(new AntPathRequestMatcher("/engenheiro/**")).hasAuthority(ENGENHEIRO)
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

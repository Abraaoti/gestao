package br.ind.cmil.gestao.configs;

import br.ind.cmil.gestao.model.enums.TipoPerfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author ti
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

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
                .csrf(csrf -> csrf.disable())
               
                .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/authenticate")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/u/editar/senha", "/u/confirmar/senha")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/empresa/avaliar/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/u/novo/cadastro", "/u/cadastro/realizado")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/u/confirmacao/cadastro", "/u/cadastro/paciente/salvar")).permitAll()
                .requestMatchers("/u/p/**").permitAll()
                //acessos privados admin  
                .requestMatchers(new AntPathRequestMatcher("/u/editar/senha", "/u/confirmar/senha")).hasAnyAuthority(ADMINISTRADOR, AUXDMINISTRATIVO, ASSISTENTE, RH)
               
                .requestMatchers(new AntPathRequestMatcher("/u/**","/projeto/**")).hasAuthority(ADMIN)
                .requestMatchers(new AntPathRequestMatcher("/administrativo/**")).hasAuthority(ADMINISTRATIVO)
                //acessos privados auxiliar administrativo                
              
                .requestMatchers(new AntPathRequestMatcher("/departamentos/**","/administrador/**")).hasAnyAuthority(ADMIN,ADMINISTRADOR)
                .requestMatchers(new AntPathRequestMatcher("/suprimento/**")).hasAnyAuthority(ADMIN,ADMINISTRADOR,COMPRADOR)
                //acessos privados auxiliar administrativo                
                .requestMatchers(new AntPathRequestMatcher("/auxiliar/**")).hasAnyAuthority(ADMIN,AUXDMINISTRATIVO)
                .requestMatchers(new AntPathRequestMatcher("/presenca/**")).hasAnyAuthority(ADMIN,AUXDMINISTRATIVO)
                //acessos privados auxiliar administrativo                
                .requestMatchers(new AntPathRequestMatcher("/assistente/**")).hasAnyAuthority(ADMIN,ASSISTENTE)
                //acessos privados financeiro                
                .requestMatchers(new AntPathRequestMatcher("/rh/**")).hasAnyAuthority(ADMIN, RH)
                .requestMatchers(new AntPathRequestMatcher("/diretoria/**")).hasAnyAuthority(ADMIN,DIRETOR)
               
                .requestMatchers(new AntPathRequestMatcher("/engenheiro/**")).hasAuthority(ENGENHEIRO)
                .requestMatchers(new AntPathRequestMatcher("/financeiro/**")).hasAuthority(FINANCEIRO)
                .requestMatchers(new AntPathRequestMatcher("/tecnico")).hasAuthority(TECNICO)
                .requestMatchers(new AntPathRequestMatcher("/comprador")).hasAuthority(COMPRADOR)
                .anyRequest()
                .authenticated()
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .defaultSuccessUrl("/home", true)
                                 .failureUrl("/login?error=true")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        //http.authenticationProvider(authenticationProvider());

        // http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        // http.headers(headers -> headers.frameOptions(frameOption -> frameOption.sameOrigin()));
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/resources/**", "/js/**", "/css/**", "/webjars/**", "/docs/**", "/image/**");

    }

}

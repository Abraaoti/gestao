package br.ind.cmil.gestao.configs;

import br.ind.cmil.gestao.model.enums.TipoPerfil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author ti
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${spring.h2.console.path}")
    private String h2ConsolePath;

    private static final String ADMIN = TipoPerfil.ADMIN.getValue();
    private static final String ADMINISTRATIVO = TipoPerfil.ADMINISTRATIVO.getValue();
    private static final String DIRETOR = TipoPerfil.DIRETOR.getValue();
    private static final String ENGENHEIRO = TipoPerfil.ENGENHEIRO.getValue();
    private static final String COMPRADOR = TipoPerfil.COMPRADOR.getValue();
    private static final String FINANCEIRO = TipoPerfil.FINANCEIRO.getValue();
    private static final String RH = TipoPerfil.RH.getValue();
    private static final String TECNICO = TipoPerfil.TECNICO.getValue();
    
    
    private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
                                 AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

   

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                //.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers(HttpMethod.GET,"/api/auth/**").permitAll()
                .requestMatchers("/api/test/**").permitAll()
                .requestMatchers(h2ConsolePath + "/**").permitAll()
                //acessos privados admin                
                .requestMatchers("/admin").hasAuthority(ADMIN)
                .requestMatchers("/administrativo").hasAuthority(ADMINISTRATIVO)
                .requestMatchers("/empresa/editar/senha", "/u/confirmar/senha").hasAnyAuthority(FINANCEIRO, ADMINISTRATIVO, ADMIN)
                .requestMatchers("/empresa/**", "/avaliacoes/**", "/avaliacao/**").hasAuthority(ADMIN)
                //acessos privados financeiro                
                .requestMatchers("/financeiro/contapagar/**").hasAnyAuthority(ADMINISTRATIVO, ADMIN, FINANCEIRO, DIRETOR)
                .requestMatchers("/empresas/**").hasAnyAuthority(ADMINISTRATIVO, ADMIN, FINANCEIRO, DIRETOR)
                .requestMatchers("/pagarcontas/**").hasAnyAuthority(ADMINISTRATIVO, ADMIN, FINANCEIRO, DIRETOR)
                .requestMatchers("/administrativo/**").hasAnyAuthority(ADMINISTRATIVO, ADMIN)
                //acessos privados diretoria
                .requestMatchers("/diretoria/**").hasAuthority(DIRETOR)
                //acessos privados tecnico
                .requestMatchers("/engenheiro").hasAuthority(ENGENHEIRO)
                .requestMatchers("/financeiro").hasAuthority(FINANCEIRO)
                .requestMatchers("/tecnico").hasAuthority(TECNICO)
                .requestMatchers("/comprador").hasAuthority(COMPRADOR)
                .requestMatchers("/rh").hasAuthority(RH)
                .anyRequest().authenticated()
                );
               // .formLogin((form) -> form
                //.loginPage("/login")
                //.loginProcessingUrl("/login")
                //.defaultSuccessUrl("/user/")
                //.permitAll()
               // )
               // .logout((logout) -> logout.permitAll())
                //.exceptionHandling().accessDeniedPage("/access-denied");

        // fix H2 database console: Refused to display ' in a frame because it set 'X-Frame-Options' to 'deny'
        http.headers().frameOptions().sameOrigin();

        //http.sessionManagement()
        //.maximumSessions(1)
        //.maxSessionsPreventsLogin(true)
        //.sessionRegistry(sessionRegistry());
        return http.build();
    }

   


    //public void configure(WebSecurity web) throws Exception {
     //   web.ignoring().requestMatchers("/resources/**");
    //}

}

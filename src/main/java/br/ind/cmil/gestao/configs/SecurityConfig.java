package br.ind.cmil.gestao.configs;

/**
 *
 * @author ti
 */
//@EnableWebSecurity
//@Configuration
public class SecurityConfig {
/**
    private static final String ADMIN = TipoPerfil.ADMIN.getValue();
    private static final String ADMINISTRATIVO = TipoPerfil.ADMINISTRATIVO.getValue();
    private static final String DIRETOR = TipoPerfil.DIRETOR.getValue();
    private static final String ENGENHEIRO = TipoPerfil.ENGENHEIRO.getValue();
    private static final String COMPRADOR = TipoPerfil.COMPRADOR.getValue();
    private static final String FINANCEIRO = TipoPerfil.FINANCEIRO.getValue();
    private static final String RH = TipoPerfil.RH.getValue();
    private static final String TECNICO = TipoPerfil.TECNICO.getValue();

    @Autowired
    private IUsuarioService userService;
    //@Autowired
    //private BCryptPasswordEncoder encoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests()
                // .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/webjars/**", "/css/**", "/js/**", "/image/**", "/docs/**", "/error**", "/api/**").permitAll()
                .requestMatchers("/", "/index", "/classificacaos/salvar", "/campanhas/avaliar/**").permitAll()
                .requestMatchers("/empresa/novo", "/u/cadastro/financeiro/salvar", "/empresa/cadastro/realizado").permitAll()
                .requestMatchers("/empresa/confirmacao/cadastro").permitAll()
                .requestMatchers("/empresa/p/**").permitAll()
                .requestMatchers("/relatorio/pdf/jr1**").permitAll()
                .requestMatchers("/classificacaos/salvar").permitAll()
                //acessos privados admin                
                .requestMatchers("/admin").hasAuthority(ADMIN)
                .requestMatchers("/administrativo").hasAuthority(ADMINISTRATIVO)
                .requestMatchers("/empresa/editar/senha", "/u/confirmar/senha").hasAnyAuthority(FINANCEIRO, ADMINISTRATIVO, ADMIN)
                .requestMatchers("/empresa/**", "/avaliacoes/**", "/avaliacao/**").hasAuthority(ADMIN)
                //acessos privados financeiro                
                .requestMatchers("/financeiro/contapagar/**").hasAnyAuthority(ADMINISTRATIVO, ADMIN, FINANCEIRO, DIRETOR)
                .requestMatchers("/empresas/**").hasAnyAuthority(ADMINISTRATIVO, ADMIN, FINANCEIRO, DIRETOR)
                .requestMatchers("/pagarcontas/**").hasAnyAuthority(ADMINISTRATIVO, ADMIN, FINANCEIRO, DIRETOR)
                .requestMatchers("/administrativo**", "/classificacaos**", "/campanhas**").hasAnyAuthority(ADMINISTRATIVO, ADMIN)
                //acessos privados diretoria
                .requestMatchers("/diretoria").hasAuthority(DIRETOR)
                //acessos privados tecnico
                .requestMatchers("/engenheiro").hasAuthority(ENGENHEIRO)
                .requestMatchers("/financeiro").hasAuthority(FINANCEIRO)
                .requestMatchers("/tecnico").hasAuthority(TECNICO)
                .requestMatchers("/comprador").hasAuthority(COMPRADOR)
                .requestMatchers("/rh").hasAuthority(RH)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                //.usernameParameter("username")
                .defaultSuccessUrl("/home", true)
                .failureUrl("/index")
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/acesso-negado")
                .and()
                .authenticationProvider(authenticationProvider());
        http.sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .sessionRegistry(sessionRegistry());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
       // auth.setPasswordEncoder(encoder);
        return auth;
    }

    @Bean
    public SessionRegistry sessionRegistry() {

        return new SessionRegistryImpl();
    }

    @Bean
    public ServletListenerRegistrationBean<?> servletListenerRegistrationBean() {

        return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
    }

    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers("/resources/**");
    }
**/
}

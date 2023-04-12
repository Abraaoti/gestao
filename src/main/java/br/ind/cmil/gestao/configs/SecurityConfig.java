package br.ind.cmil.gestao.configs;

/**
 *
 * @author ti
 */
//@EnableWebSecurity
//@Configuration
public class SecurityConfig {
/**
    private static final String ANALISTA = PerfilTipo.ANALISTA.getDesc();
    private static final String ADMIN = PerfilTipo.ADMIN.getDesc();
    private static final String ADMINISTRADOR = PerfilTipo.ADMINISTRADOR.getDesc();
    private static final String DIRETORIA = PerfilTipo.DIRETORIA.getDesc();
    private static final String ENGENHEIRO = PerfilTipo.ENGENHEIRO.getDesc();
    private static final String COMPRADOR = PerfilTipo.COMPRADOR.getDesc();
    private static final String FINANCEIRO = PerfilTipo.FINANCEIRO.getDesc();
    private static final String RH = PerfilTipo.RH.getDesc();
    private static final String TECNICO = PerfilTipo.TECNICO.getDesc();

    @Autowired
    private IUsuarioService userService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/webjars/**", "/css/**", "/js/**", "/image/**", "/docs/**", "/error**","/api/**").permitAll()
                .requestMatchers("/", "/index", "/classificacaos/salvar", "/campanhas/avaliar/**").permitAll()
                .requestMatchers("/empresa/novo", "/u/cadastro/financeiro/salvar", "/empresa/cadastro/realizado").permitAll()
                .requestMatchers("/empresa/confirmacao/cadastro").permitAll()
                .requestMatchers("/empresa/p/**").permitAll()
                .requestMatchers("/relatorio/pdf/jr1**").permitAll()
                .requestMatchers("/classificacaos/salvar").permitAll()
                
                //acessos privados admin                
                .requestMatchers("/admin").hasAuthority(ADMIN)
                .requestMatchers("/administratidor").hasAuthority(ADMINISTRADOR)
                .requestMatchers("/empresa/editar/senha", "/u/confirmar/senha").hasAnyAuthority(FINANCEIRO, ADMINISTRADOR, ADMIN)
                .requestMatchers("/empresa/**", "/avaliacoes/**", "/avaliacao/**").hasAuthority(ADMIN)
                //acessos privados financeiro                
                .requestMatchers("/financeiro/contapagar/**").hasAnyAuthority(ADMINISTRADOR, ADMIN, FINANCEIRO, DIRETORIA)
                .requestMatchers("/empresas/**").hasAnyAuthority(ADMINISTRADOR, ADMIN, FINANCEIRO, DIRETORIA)
                .requestMatchers("/pagarcontas/**").hasAnyAuthority(ADMINISTRADOR, ADMIN, FINANCEIRO, DIRETORIA)
                .requestMatchers("/administrativo**", "/classificacaos**", "/campanhas**").hasAnyAuthority(ADMINISTRADOR, ADMIN)
                //acessos privados diretoria
                .requestMatchers("/diretoria").hasAuthority(DIRETORIA)
                //acessos privados tecnico
                .requestMatchers("/engenheiro").hasAuthority(ENGENHEIRO)
                .requestMatchers("/financeiro").hasAuthority(FINANCEIRO)
                .requestMatchers("/tecnico").hasAuthority(TECNICO)
                .requestMatchers("/comprador").hasAuthority(COMPRADOR)
                //acessos privados rh
                .requestMatchers("/analista").hasAuthority(ANALISTA)
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

    /*@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(encoder);
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
*/
}

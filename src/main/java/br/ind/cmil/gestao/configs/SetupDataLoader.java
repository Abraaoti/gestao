package br.ind.cmil.gestao.configs;

/**
 *
 * @author abraao
 */
//@Component
public class SetupDataLoader{// implements ApplicationListener<ContextRefreshedEvent> {
/**
    boolean alreadySetup = false;
    @Autowired
    private IUsuarioRepository ur;
    @Autowired
    private IPerfilRepository per;
    @Autowired
    private IPrivilegioRepository pr;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        Privilegio readPrivilege = createPrivilegeIfNotFound(TipoPrivilegio.LER.getValue());
        Privilegio writePrivilege = createPrivilegeIfNotFound(TipoPrivilegio.GRAVAR.getValue());

        Set<Privilegio> adminPrivileges = new HashSet<>();
        adminPrivileges.add(readPrivilege);
        adminPrivileges.add(writePrivilege);

        createRoleIfNotFound(TipoPerfil.ADMIN.getValue(), adminPrivileges);
        createRoleIfNotFound(TipoPerfil.USUARIO.getValue(), Arrays.asList(readPrivilege));

        Perfil adminRole = per.findByTipoPerfil(TipoPerfil.ADMIN.getValue()).get();
        Usuario user = new Usuario();
        user.setNome("test");
        user.setPassword(passwordEncoder.encode("test"));
        user.setEmail("test@test.com");
        user.setPerfis((Set<Perfil>) Arrays.asList(adminRole));
        user.setAtivo(alreadySetup);
        user.setDataCadastro(LocalDateTime.now());
        user.setVerificador(null);
        ur.save(user);

        alreadySetup = true;
    }

    @Transactional(readOnly = false)
    Privilegio createPrivilegeIfNotFound(String name) {

        Privilegio privilege = pr.findByTipoPrivilegio(name).get();
        if (privilege == null) {
            privilege = new Privilegio(name);
            pr.save(privilege);
        }
        return privilege;
    }

    @Transactional(readOnly = false)
    Perfil createRoleIfNotFound(String name, Collection<Privilegio> privileges) {

        Perfil role = per.findByTipoPerfil(name).get();
        if (role == null) {
            role = new Perfil(name);
            role.setPrivilegios(privileges);
            per.save(role);
        }
        return role;
    }
*/
}

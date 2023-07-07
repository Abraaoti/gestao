package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.core.email.services.interfaces.IEmailService;
import br.ind.cmil.gestao.exceptions.AcessoNegadoException;
import br.ind.cmil.gestao.exceptions.UsuarioExistenteException;
import br.ind.cmil.gestao.exceptions.UsuarioNotFoundException;
import br.ind.cmil.gestao.model.dto.mappers.PerfilMapper;
import br.ind.cmil.gestao.model.dto.request.RegistrarUsuario;
import br.ind.cmil.gestao.model.dto.response.UsuarioResponse;
import br.ind.cmil.gestao.model.entidades.Perfil;
import br.ind.cmil.gestao.model.entidades.Usuario;
import br.ind.cmil.gestao.model.repositorys.IUsuarioRepository;
import br.ind.cmil.gestao.model.services.interfaces.IPerfilService;
import br.ind.cmil.gestao.model.services.interfaces.IUsuarioService;
import br.ind.cmil.gestao.model.services.mappres.RegistrarUsuarioMapper;
import jakarta.mail.MessagingException;
import java.util.Base64;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
@Service
//@Transactional
public class UsuarioPadraoService implements IUsuarioService {
    
    private final IUsuarioRepository ur;
    private final IPerfilService ps;
    private final PerfilMapper pm;
    private final PasswordEncoder encoder;
    private final IEmailService email;
    private final RegistrarUsuarioMapper rm;
    
    public UsuarioPadraoService(IUsuarioRepository ur, IPerfilService ps, PerfilMapper pm, PasswordEncoder encoder, IEmailService email, RegistrarUsuarioMapper rm) {
        this.ur = ur;
        this.ps = ps;
        this.pm = pm;
        this.encoder = encoder;
        this.email = email;
        this.rm = rm;
    }
    
    @Override
    @Transactional(readOnly = false)
    public void register(RegistrarUsuario request, String siteURL) {
        request.id();
        validarAtributos(request);
        // Optional<Usuario> usuario = ur.findByNomeOrEmail(request.nome(), request.email());

        // if (usuario.isPresent()) {
        //   throw new UsuarioExistenteException(request.email(), "Usuário já existente! ");
        //}
        Usuario user = rm.toEntity(request);
        
        Set<Perfil> roles = ps.perfis(request.role());        
        user.setPassword(encoder.encode(request.password()));
        user.setPerfis(roles);

        //this.email.confirmarCadastro(user, siteURL);
        ur.save(user);
    }
    
    @Override
    public UsuarioResponse buscarPorId(Long id) {
        Usuario u = ur.findById(id).orElseThrow(() -> new UsuarioNotFoundException(String.valueOf(id), "Este id: não consta no nosso banco de dados "));
        
        Set<String> perfis = new HashSet<>();
        for (Perfil perfi : u.getPerfis()) {
            Perfil p = perfi;
            perfis.add(p.getTp().getValue());
        }
        
        return new UsuarioResponse(u.getId(), u.getNome(), u.getEmail(), perfis);
        
    }
    
    private void updateCustomerPerfil(Usuario userEntity) {
        Perfil group = pm.toEntity(ps.buscarPerfilPorNome("usuário"));
        // userEntity.addUsuarioPerfis(group);
    }
    
    @Override
    public void alterarSenha(Usuario usuario, String s1) {
        usuario.setPassword(new BCryptPasswordEncoder().encode(s1));
        ur.save(usuario);
    }
    
    @Override
    public Usuario buscarEmailAtivo(String email) {
        return ur.findByEmailAndAtivo(email).get();
    }
    
    @Override
    public void redefinirSenha(String email) throws MessagingException {
        Usuario usuario = buscarEmailAtivo(email);
        String verificador = RandomStringUtils.randomAlphanumeric(6);
        
        usuario.setVerificador(verificador);
        this.email.redefinirSenha(email, verificador);
    }
    
    @Override
    public void ativarCadastro(String codigo) {
        Base64.Decoder decode = Base64.getDecoder();
        String email = new String(decode.decode(codigo));
        Usuario usuario = ur.findByEmail(email).get();
        if (usuario.getId() == null) {
            throw new AcessoNegadoException(codigo, "Não foi possível ativar seu cadastro. Entre em contato com o suporte!");
        }
        usuario.setAtivo(true);
    }
    
    private void validarAtributos(RegistrarUsuario request) {
        Optional<Usuario> usuario = ur.findByEmail(request.nome());
        if (usuario.isPresent() && !Objects.equals(usuario.get().getId(), request.id())) {
            throw new DataIntegrityViolationException("nome já cadastro no sistema!");
        }
        usuario = ur.findByEmail(request.email());
        if (usuario.isPresent() && !Objects.equals(usuario.get().getId(), request.id())) {
            throw new DataIntegrityViolationException("E-mail já cadastro no sistema!");
        }
        
    }
    
    @Override
    public boolean verify(String verificationCode) {
        Usuario user = ur.findByVerificationCode(verificationCode);
        
        if (user == null || user.isAtivo()) {
            return false;
        } else {
            user.setVerificador(null);
            user.setAtivo(true);
            ur.save(user);
            return true;
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email) {
        return ur.findByNomeOrEmail(email, email).orElseThrow(() -> new UsuarioNotFoundException(email, " Este usuário não consta no nosso banco de dados "));
    }
    /**
     * @Transactional(readOnly = true) public UserDetails
     * loadUserByUsername(String username) throws UsernameNotFoundException {
     * Usuario user = buscarPorEmail(username); if (user != null) { return new
     * org.springframework.security.core.userdetails.User(user.getEmail(),
     * user.getPassword(), true, true, true, true,
     * mapRolesToAuthorities(user.getPerfis())); } else { throw new
     * UsernameNotFoundException("O usuário ou senha inválida!"); } }
     *
     * private Collection< ? extends GrantedAuthority>
     * mapRolesToAuthorities(Collection<Perfil> roles) {
     * Collection< ? extends GrantedAuthority> mapRoles = roles.stream()
     * .map(role -> new SimpleGrantedAuthority(role.getTp().getValue()))
     * .collect(Collectors.toSet()); return mapRoles; }
     *
     *
     * @Transactional(readOnly = false) public MessageResponse
     * register(RegistrarUsuario request) {
     *
     * Set<Perfil> roles = new HashSet<>();//ps.perfis(request.role()); Usuario
     * user = rm.toEntity(request); PerfilDTO pe =
     * ps.buscarPerfilPorNome("admin"); //Perfil p = pm.toEntity(pe);
     *
     * if (pe != null) { roles.add(pm.toEntity(pe));
     * //p.setTp(pm.convertPerfilValue("usuário")); } Perfil p = new Perfil();
     * p.setTp(pm.convertPerfilValue("usuário")); roles.add(p);
     * user.setPassword(passwordEncoder.encode(request.password()));
     * user.setPerfis(roles);
     *
     * //this.email.confirmarCadastro(user, siteURL); ur.save(user); var jwt =
     * jwtService.generateToken((UserDetails) user); return new
     * MessageResponse(jwt); }*
     */
}

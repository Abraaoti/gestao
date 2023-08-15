package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.exceptions.AcessoNegadoException;
import br.ind.cmil.gestao.exceptions.UsuarioNotFoundException;
import br.ind.cmil.gestao.model.dto.mappers.PerfilMapper;
import br.ind.cmil.gestao.model.dto.request.RegistrarUsuario;
import br.ind.cmil.gestao.model.entidades.Perfil;
import br.ind.cmil.gestao.model.entidades.Usuario;
import br.ind.cmil.gestao.model.repositorys.IUsuarioRepository;
import br.ind.cmil.gestao.model.services.interfaces.IPerfilService;
import br.ind.cmil.gestao.model.services.interfaces.IUsuarioService;
import br.ind.cmil.gestao.model.dto.mappers.UsuarioMapper;
import jakarta.mail.MessagingException;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import static java.util.stream.Collectors.toSet;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {

    private final IUsuarioRepository ur;
    private final IPerfilService ps;
    private final PerfilMapper pm;
    private final PasswordEncoder encoder;
    private final EmailServiceImp email;
    private final UsuarioMapper rm;

    //public void increaseFailedAttempts(Usuario user) {
    //   int newFailAttempts = user.getFailedLoginAttempts() + 1;
    //    ur.updateFailedAttempts(newFailAttempts, user.getEmail());
    //}
    // public void resetFailedAttempts(String email) {
    // ur.updateFailedAttempts(0, email);
    //}
    @Override
    @Transactional(readOnly = false)
    public void register(RegistrarUsuario request, String siteURL) throws MessagingException {
        request.id();
        validarAtributos(request);
        Usuario user = rm.toEntity(request);
        
       // Set<Perfil> roles = request.perfis().stream().map(tipoPerfil -> ps.getOrCreate(tipoPerfil)).collect(toSet());

       Set<Perfil> roles = ps.perfis(request.perfis());
        user.setPassword(encoder.encode(request.password()));
        user.setPerfis(roles);
        ur.save(user);
        //emailDeConfirmacaoDeCadastro(usuarioEmail.getEmail(),siteURL);
    }

    public void emailDeConfirmacaoDeCadastro(String email, String url) throws MessagingException {
        Base64.Encoder encode = Base64.getEncoder();
        String codigo = encode.encodeToString(email.getBytes());
        this.email.enviarEmail(email, url, codigo);
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return ur.findByUsuarioId(id).orElseThrow(() -> new UsuarioNotFoundException(String.valueOf(id), "Este id: não consta no nosso banco de dados "));

        // Set<String> perfis = new HashSet<>();
        //for (Perfil perfi : u.getPerfis()) {
        //Perfil p = perfi;
        // perfis.add(p.getTp().getValue());
        //}
        //return new UsuarioResponse(u.getId(), u.getNome(), u.getEmail(), perfis);
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
        Optional<Usuario> usuario = ur.findByNome(request.nome());
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

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> usuarios(Pageable pageable) {
        // List<Usuario> u =  (List<Usuario>) ur.searchAll(pageable);
        // List<Usuario> u =  ur.searchAll(pageable).stream().collect(Collectors.toList());

        Set<String> roles = new HashSet<>();
        // for (Perfil perfi : u.getPerfis()) {
        //     Perfil p = perfi;
        //    roles.add(p.getTp().getValue());
        // }

        //return new UsuarioResponse(u.getId(), u.getNome(), u.getEmail(), roles);
        // return ur.searchAll(pageable).stream().map(rm::toDTO).collect(Collectors.toList());
        return null;// ur.searchAll(pageable).stream().map(rm::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        return ur.searchAll();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return   ur.findByNomeOrEmail(username, username).get();
        //Usuario usuario = ur.findByNomeOrEmail(username, username).get();
       // return new User(
         //       usuario.getEmail(),
        /// ////       usuario.getPassword(),
        //        AuthorityUtils.createAuthorityList(getAtuthorities((List<Perfil>) usuario.getPerfis()))
        //);
    }

    private String[] getAtuthorities(List<Perfil> perfis) {
        String[] authorities = new String[perfis.size()];
        for (int i = 0; i < perfis.size(); i++) {
            authorities[i] = perfis.get(i).getAuthority();
        }
        return authorities;
    }
}

package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.exceptions.UsuarioNotFoundException;
import br.ind.cmil.gestao.model.datatables.Datatables;
import br.ind.cmil.gestao.model.datatables.DatatablesColunas;
import br.ind.cmil.gestao.model.dto.RegistrarUsuario;
import br.ind.cmil.gestao.model.dto.UtenteDTO;
import br.ind.cmil.gestao.model.entidades.Perfil;
import br.ind.cmil.gestao.model.entidades.Usuario;
import br.ind.cmil.gestao.model.repositorys.IUsuarioRepository;
import br.ind.cmil.gestao.model.services.interfaces.IPerfilService;
import br.ind.cmil.gestao.model.services.interfaces.IUsuarioService;
import br.ind.cmil.gestao.model.dto.mappers.UsuarioMapper;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final PasswordEncoder encoder;
    private final EmailServiceImp email;
    private final UsuarioMapper rm;
    private final IPerfilService ps;
    private final Datatables datatables;

    //public void increaseFailedAttempts(Usuario user) {
    //   int newFailAttempts = user.getFailedLoginAttempts() + 1;
    //    ur.updateFailedAttempts(newFailAttempts, user.getEmail());
    //}
    // public void resetFailedAttempts(String email) {
    // ur.updateFailedAttempts(0, email);
    //}
    @Transactional(readOnly = false)
    @Override
    public void register(RegistrarUsuario request, String siteURL) throws MessagingException {

        if (request.id() != null) {
            update(request);
        }
        validarAtributos(request);
        Usuario user = rm.toEntity(request);
        Set<Perfil> roles = ps.perfis(request.perfis());
        // if (roles.size() > 2 || roles.containsAll(Arrays.asList(new Perfil("admin"), new Perfil(ps.tipoPerfil("usuario"))))
        //       || roles.containsAll(Arrays.asList(new Perfil(ps.tipoPerfil("administrativo")), new Perfil(ps.tipoPerfil("usuario"))))) {
        //    throw new RuntimeException("Usuário não pode ser Admin e/ou Administrativo.");
        // }

        user.setPassword(encoder.encode(request.password()));
        user.setPerfis(roles);
        Usuario usuario = ur.save(user);
        emailDeConfirmacaoDeCadastro(usuario.getEmail(), siteURL);

    }

    public void update(RegistrarUsuario request) {
        Usuario us = ur.findById(request.id()).get();
        us.setNome(request.nome());
        us.setDataCadastro(us.getDataCadastro());
        us.setUpdatedAt(LocalDateTime.now());
        us.setEmail(request.email());
        us.setAtivo(request.ativo());
        us.setPassword(new BCryptPasswordEncoder().encode(request.password()));
        us.setVerificador(request.verificador());
        Set<Perfil> roles = ps.perfis(request.perfis());
        us.setPerfis(roles);
        ur.save(us);

    }

    @Transactional(readOnly = false)
    public void emailDeConfirmacaoDeCadastro(String email, String url) throws MessagingException {
        Base64.Encoder encode = Base64.getEncoder();
        String codigo = encode.encodeToString(email.getBytes());
        this.email.enviarEmail(email, url, codigo);
    }

    @Override
    @Transactional(readOnly = true)
    public RegistrarUsuario buscarPorId(Long id) {
        return ur.findByUsuarioId(id).map(rm::toDTO).orElseThrow(() -> new UsuarioNotFoundException(String.valueOf(id), "Este id: não consta no nosso banco de dados "));

    }

    @Override
    @Transactional(readOnly = false)
    public void alterarSenha(Usuario usuario, String s1) {
        usuario.setPassword(new BCryptPasswordEncoder().encode(s1));
        ur.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public RegistrarUsuario buscarEmailAtivo(String email) {
        return ur.findByEmailAndAtivo(email).map(rm::toDTO).orElseThrow(() -> new UsuarioNotFoundException(email, " Este usuário não consta no nosso banco de dados "));

    }

    @Transactional(readOnly = false)
    @SuppressWarnings("empty-statement")
    @Override
    public void redefinirSenha(String email) throws MessagingException {
        Usuario usuario = rm.toEntity(buscarEmailAtivo(email));
        String verificador = RandomStringUtils.randomAlphanumeric(6);

        usuario.setVerificador(verificador);
        this.email.redefinirSenha(email, verificador);
    }

    @Override
    @Transactional(readOnly = false)
    public void ativarCadastro(String codigo) {
        Base64.Decoder decode = Base64.getDecoder();
        String mail = new String(decode.decode(codigo));
        Usuario usuario = ur.findByNomeOrEmail(mail, mail).orElseThrow(() -> new UsuarioNotFoundException(mail, " Este usuário não consta no nosso banco de dados "));
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
    public RegistrarUsuario buscarPorEmail(String email) {
        return ur.findByNomeOrEmail(email, email).map(rm::toDTO).orElseThrow(() -> new UsuarioNotFoundException(email, " Este usuário não consta no nosso banco de dados "));
    }

    @Override
    @Transactional(readOnly = true)
    public Set<RegistrarUsuario> getUsuarios(Pageable pageable) {

        return ur.searchAll(pageable).stream().map(rm::toDTO).collect(Collectors.toSet());
    }

    @Override
    public RegistrarUsuario preEditarCadastroDadosPessoais(Long usuarioId, Long[] perfisId) {
       return ur.findByIdAndPerfis(usuarioId, perfisId).map(rm::toDTO).orElseThrow(() -> new UsernameNotFoundException("Usuário inexistente!"));
    }

    @Transactional(readOnly = false)
    @Override
    public void salvarUsuarioGeral(RegistrarUsuario request, String siteURL) throws MessagingException {
        if (request.id() != null) {
            update(request);
        }
        validarAtributos(request);
        Usuario user = rm.toEntity(request);
        Set<Perfil> roles = ps.perfis(request.perfis());
        user.setPassword(encoder.encode(request.password()));
        user.setPerfis(roles);
        Usuario usuario = ur.save(user);
        emailDeConfirmacaoDeCadastro(usuario.getEmail(), siteURL);

    }

    @Override
    @Transactional(readOnly = false)
    public void salvarUsuarioExterno(UtenteDTO usuario) throws MessagingException {

    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.USUARIOS);
        Page<Usuario> page = datatables.getSearch().isEmpty()
                ? ur.findAll(datatables.getPageable())
                : ur.findByEmailOrPerfil(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }
    

    /**
     * Set<String> perfis = usuario.perfis(); if (perfis.size() > 2 ||
     * perfis.containsAll(Arrays.asList(new Perfil(1L), new Perfil(3L))) ||
     * perfis.containsAll(Arrays.asList(new Perfil(2L), new Perfil(3L)))) {
     * attr.addFlashAttribute("falha", "auxiliar administrativo não pode ser
     * Admin e/ou Assistente."); attr.addFlashAttribute("usuario", usuario); }
     * else { try { service.salvarUsuario(usuario);
     * attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!"); }
     * catch (DataIntegrityViolationException ex) {
     * attr.addFlashAttribute("falha", "Cadastro não realizado, email já
     * existente."); } }*
     */
}

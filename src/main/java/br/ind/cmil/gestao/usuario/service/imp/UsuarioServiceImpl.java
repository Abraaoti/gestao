package br.ind.cmil.gestao.usuario.service.imp;

import br.ind.cmil.gestao.datatables.Datatables;
import br.ind.cmil.gestao.datatables.DatatablesColunas;
import br.ind.cmil.gestao.exceptions.UsuarioNotFoundException;
import br.ind.cmil.gestao.jwt.email.imp.EmailServiceImp;
import br.ind.cmil.gestao.perfil.domain.Perfil;
import br.ind.cmil.gestao.perfil.service.PerfilService;
import br.ind.cmil.gestao.usuario.domain.Usuario;
import br.ind.cmil.gestao.usuario.mapper.UsuarioMapper;
import br.ind.cmil.gestao.usuario.model.CadastroExternoDTO;
import br.ind.cmil.gestao.usuario.model.CriarUsuarioDTO;
import br.ind.cmil.gestao.usuario.repository.UsuarioRepository;
import br.ind.cmil.gestao.usuario.service.UsuarioService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
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
 * @author ti
 */
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;
    private final EmailServiceImp email;
    private final UsuarioMapper rm;
    private final PerfilService ps;
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
    public void register(CriarUsuarioDTO request) {
        validarAtributos(request);

        Usuario user = rm.toEntity(request);
        if (user.getId() != null) {
            update(user);
        }

        usuarioRepository.save(user);

    }

    public void update(Usuario request) {
        Usuario usuario = usuarioRepository.findById(request.getId()).get();
        usuario.setNome(request.getNome());
        usuario.setDataCadastro(usuario.getDataCadastro());
        usuario.setUpdatedAt(LocalDateTime.now());
        usuario.setEmail(request.getEmail());
        usuario.setAtivo(request.isAtivo());
        usuario.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        usuario.setVerificador(request.getVerificador());
       // List<Perfil> perfis = request.getPerfis().stream().map(perfil -> perfil).collect(Collectors.toList());
        // List<Perfil> roles = ps.perfis(request.getPerfis());
        usuario.setPerfis(request.getPerfis());
        usuario.setId(request.getId());
        usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = false)
    public void emailDeConfirmacaoDeCadastro(String email, String url) throws MessagingException {
        Base64.Encoder encode = Base64.getEncoder();
        String codigo = encode.encodeToString(email.getBytes());
        this.email.enviarEmail(email, url, codigo);
    }

    @Override
    @Transactional(readOnly = true)
    public CriarUsuarioDTO buscarPorId(Long id) {
        return usuarioRepository.findByUsuarioId(id).map(rm::toDTO).orElseThrow(() -> new UsuarioNotFoundException(String.valueOf(id), "Este id: não consta no nosso banco de dados "));

    }

    @Override
    @Transactional(readOnly = false)
    public void alterarSenha(Usuario usuario, String s1) {
        usuario.setPassword(new BCryptPasswordEncoder().encode(s1));
        usuarioRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public CriarUsuarioDTO buscarEmailAtivo(String email) {
        return usuarioRepository.findByEmailAndAtivo(email).map(rm::toDTO).orElseThrow(() -> new UsuarioNotFoundException(email, " Este usuário não consta no nosso banco de dados "));

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
        String login = new String(decode.decode(codigo));
        Usuario usuario = usuarioRepository.findByLogin(login).orElseThrow(() -> new UsuarioNotFoundException(login, " Este usuário não consta no nosso banco de dados "));
        usuario.setAtivo(true);
    }

    private void validarAtributos(CriarUsuarioDTO request) {

        Optional<Usuario> usuario = usuarioRepository.findByNome(request.nome());
        if (usuario.isPresent() && !Objects.equals(usuario.get().getId(), request)) {
            throw new DataIntegrityViolationException("nome já cadastro no sistema!");
        }
        usuario = usuarioRepository.findByEmail(request.email());
        if (usuario.isPresent() && !Objects.equals(usuario.get().getId(), request)) {
            throw new DataIntegrityViolationException("E-mail já cadastro no sistema!");
        }

    }

    @Override
    public boolean verify(String verificationCode) {
        Usuario user = usuarioRepository.findByVerificationCode(verificationCode);

        if (user == null || user.isAtivo()) {
            return false;
        } else {
            user.setVerificador(null);
            user.setAtivo(true);
            usuarioRepository.save(user);
            return true;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public CriarUsuarioDTO buscarPorEmail(String login) {
        return usuarioRepository.findByLogin(login).map(rm::toDTO).orElseThrow(() -> new UsuarioNotFoundException(login, " Este usuário não consta no nosso banco de dados "));
    }

    @Override
    @Transactional(readOnly = true)
    public Set<CriarUsuarioDTO> getUsuarios(Pageable pageable) {

        return usuarioRepository.searchAll(pageable).stream().map(rm::toDTO).collect(Collectors.toSet());
    }

    @Override
    public CriarUsuarioDTO preEditarCadastroDadosPessoais(Long usuarioId, Long[] perfisId) {
        return usuarioRepository.findByIdAndPerfis(usuarioId, perfisId).map(rm::toDTO).orElseThrow(() -> new UsernameNotFoundException("Usuário inexistente!"));
    }

    @Transactional(readOnly = false)
    @Override
    public void salvarUsuarioGeral(CriarUsuarioDTO request, String siteURL) throws MessagingException {
        if (request != null) {
            //update(request);
        }
        validarAtributos(request);
        Usuario user = rm.toEntity(request);
        List<Perfil> roles = ps.perfis(request.perfis());
        user.setPassword(encoder.encode(request.password()));
        user.setPerfis(roles);
        Usuario usuario = usuarioRepository.save(user);
        emailDeConfirmacaoDeCadastro(usuario.getEmail(), siteURL);

    }

    @Override
    @Transactional(readOnly = false)
    public void salvarUsuarioExterno(CadastroExternoDTO usuario) throws MessagingException {

    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.USUARIOS);
        Page<Usuario> page = datatables.getSearch().isEmpty()
                ? usuarioRepository.findAll(datatables.getPageable())
                : usuarioRepository.findByEmailOrPerfil(datatables.getSearch(), datatables.getPageable());
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

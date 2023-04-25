package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.exception.RecordNotFoundException;
import br.ind.cmil.gestao.model.dto.PerfilDTO;
import br.ind.cmil.gestao.model.dto.UsuarioDTO;
import br.ind.cmil.gestao.model.dto.mappers.PerfilMapper;
import br.ind.cmil.gestao.model.dto.mappers.UsuarioMapper;
import br.ind.cmil.gestao.model.entidades.Perfil;
import br.ind.cmil.gestao.model.entidades.Usuario;
import br.ind.cmil.gestao.model.repositorys.IUsuarioRepository;
import br.ind.cmil.gestao.model.services.interfaces.IUsuarioService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author abraao
 */
@Service
@AllArgsConstructor
@Transactional
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private final IUsuarioRepository ur;
    //@Autowired
    //private final EmailService es;
    // @Autowired
    //private final IPerfilRepository iPerfilRepo;
    @Autowired
    private final UsuarioMapper um;
    @Autowired
    private final PerfilMapper pm;
    private final EmailServiceImp es;
    //private final AppConfig appConfig;

    @Override
    public List<UsuarioDTO> list() {
        //List<Usuario> usuarios = ur.findAll();
        
        //return usuarios.stream().map((usuer)->um.toDTO(usuer)).collect(Collectors.toList());
        return ur.findAll().stream().map(um::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<Usuario> getUsuarios() {
        return ur.usuarios();
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public UsuarioDTO save(UsuarioDTO usuario) {
        Usuario us = um.toEntity(usuario);
        us.setDataCadastro(LocalDateTime.now());
        us.setPassword(new BCryptPasswordEncoder().encode(usuario.password()));
        return um.toDTO(ur.save(us));

    }

    @Override

    public UsuarioDTO buscarPorId(Long id) {
        return ur.findById(id).map(um::toDTO).orElseThrow(() -> new RecordNotFoundException(id));
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public UsuarioDTO preEditarCadastroDadosPessoais(Long usuarioId, Long[] perfisId) {
        Perfil p = new Perfil();
        Usuario us = ur.findByIdAndPerfis(usuarioId, perfisId).orElseThrow(() -> new UsernameNotFoundException("Usuário inexistente!"));
        if (us.getPerfis().contains(p.getTp().ADMIN.getValue()) && !us.getPerfis().contains(p.getTp().DIRETOR.getValue())) {

            return null;// new ModelAndView("usuario/cadastro", "usuario", us);
        } else if (us.getPerfis().contains(p.getTp().FUNCIONARIO.getValue())) {

            return null;
        }
        return null;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public UsuarioDTO buscarPorEmail(String email) {
        return ur.findByEmail(email).map(um::toDTO).orElseThrow(() -> new UsernameNotFoundException("Usuario " + email + " não encontrado."));
    }

    @Override
    public void delete(Long id) {
        Usuario usuario = ur.findById(id).get();
        // Usuario usuario = ur.delete(ur.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));

        usuario.getPerfis().forEach((Perfil role) -> {
            usuario.getPerfis().remove(role.getTp().getValue());
        });
        ur.delete(usuario);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public void alterarSenha(UsuarioDTO usuario, String senha) {
        Usuario usa = um.toEntity(usuario);
        usa.setPassword(new BCryptPasswordEncoder().encode(senha));
        ur.save(usa);
    }

    @Override
    public UsuarioDTO update(UsuarioDTO usuario) {
        Usuario us = ur.findById(usuario.id()).get();
        us.setAtivo(usuario.ativo());
        us.setDataCadastro(usuario.dataCadastro());
        us.setEmail(usuario.email());
        us.setPassword(usuario.password());
        List<Perfil> perfis = new ArrayList<>();

        for (PerfilDTO perfi : usuario.perfis()) {
            Perfil p = pm.toEntity(perfi);
            perfis.add(p);
        }
        if (us.getPerfis().containsAll(perfis)) {
            us.setPerfis(perfis);
        }
        us.setVerificador(usuario.verificador());
        return um.toDTO(ur.save(us));
    }

    @Override
    public UsuarioDTO buscarPorEmailEAtivo(String email) {
        return ur.findByEmailAndAtivo(email).map(um::toDTO).orElseThrow(() -> new UsernameNotFoundException("Usuario " + email + " não encontrado."));
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = false)
    @Override
    @SuppressWarnings("empty-statement")
    public void pedidoRedefinicaoDeSenha(String email) {

        try {
            UsuarioDTO usdto = buscarPorEmailEAtivo(email);
            Usuario usuario = um.toEntity(usdto);

            String verificador = RandomStringUtils.randomAlphanumeric(6);

            usuario.setVerificador(verificador);

            es.enviarPedidoRedefinicaoSenha(email, verificador);
        } catch (MessagingException ex) {
            Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void ativarCadastroFuncionario(String codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioDTO us = buscarPorEmailEAtivo(username);
        Usuario usuario = um.toEntity(us);
        return new User(usuario.getEmail(), usuario.getPassword(), true, true, true, true, AuthorityUtils.createAuthorityList(getAtuthorities(us.perfis())));

    }

    private String[] getAtuthorities(List<PerfilDTO> perfis) {
        String[] authorities = new String[perfis.size()];
        for (int i = 0; i < perfis.size(); i++) {
            authorities[i] = perfis.get(i).p();
        }
        return authorities;
    }

    @Override
    public UsuarioDTO updatePassword(User user, String s1, String s2, String s3) {
        if (!s1.equals(s2)) {
            throw new UnsupportedOperationException("Senha não conferem.");
        }
        UsuarioDTO us = buscarPorEmail(user.getUsername());
        Usuario usuario = um.toEntity(us);

        if (!IUsuarioService.isSenhaCorreta(s3, usuario.getPassword())) {
            throw new UnsupportedOperationException("Senha atual não confere, tente novamente.");
        }
        this.alterarSenha(us, s1);
        throw new UnsupportedOperationException("Senha alterada com sucesso.");
    }
}

package br.ind.cmil.gestao.usuario.service.imp;

import br.ind.cmil.gestao.usuario.details.UsuarioDetailsImpl;
import br.ind.cmil.gestao.usuario.domain.Usuario;
import br.ind.cmil.gestao.usuario.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author abraaocalelessocassi
 */
@Service
public class UsuarioDetailsServiceimp implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsServiceimp(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByLogin(username).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        return new UsuarioDetailsImpl(user);
    }

}

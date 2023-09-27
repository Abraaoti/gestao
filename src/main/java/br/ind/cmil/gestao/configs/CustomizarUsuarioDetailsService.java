package br.ind.cmil.gestao.configs;

import br.ind.cmil.gestao.model.entidades.Perfil;
import br.ind.cmil.gestao.model.entidades.Usuario;
import br.ind.cmil.gestao.model.repositorys.IUsuarioRepository;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
@Service
public class CustomizarUsuarioDetailsService implements UserDetailsService {

    private final IUsuarioRepository ur;

    public CustomizarUsuarioDetailsService(IUsuarioRepository ur) {
        this.ur = ur;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = ur.findByNomeOrEmail(username, username).get();     

        if (usuario != null) {
            return new org.springframework.security.core.userdetails.User(usuario.getEmail(),
                    usuario.getPassword(),
                    mapRolesToAuthorities(usuario.getPerfis()));
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection<Perfil> roles) {
        Collection< ? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getTp().getValue().toUpperCase()))
                .collect(Collectors.toList());
        return mapRoles;
    }

}

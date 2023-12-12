package br.ind.cmil.gestao.securitys;

import br.ind.cmil.gestao.domain.Perfil;
import br.ind.cmil.gestao.domain.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author abraao
 */
public class UsuarioDetalhesimp implements UserDetails {

    private final Usuario usuario;

    public UsuarioDetalhesimp(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (Perfil userPerfil : usuario.getPerfis()) {
            authorities.add(new SimpleGrantedAuthority(userPerfil.getTp().getValue()));
        }

        return authorities;

    }

    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getNome();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

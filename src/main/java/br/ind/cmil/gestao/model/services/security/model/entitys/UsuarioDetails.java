package br.ind.cmil.gestao.model.services.security.model.entitys;

import br.ind.cmil.gestao.model.base.Entidade;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author abraao
 */
public class UsuarioDetails extends Entidade{//  implements UserDetails{

   
    private String username;

    private String email;

    @JsonIgnore
    private String password;

   /** private Collection<? extends GrantedAuthority> authorities;

    public UsuarioDetails(Long id, String username, String email, String password,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UsuarioDetails build(Usuario user) {
        List<GrantedAuthority> authorities = user.getPerfis().stream()
                .map(role -> new SimpleGrantedAuthority(role.getTp().name()))
                .collect(Collectors.toList());

        return new UsuarioDetails(
                user.getId(),
                user.getNome(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }


    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

   

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
         return authorities;
    }
*/
}

package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.model.dto.PerfilDTO;
import br.ind.cmil.gestao.model.entidades.Perfil;
import br.ind.cmil.gestao.model.entidades.Usuario;
import br.ind.cmil.gestao.model.enums.TipoPerfil;
import br.ind.cmil.gestao.model.repositorys.IUsuarioRepository;
import br.ind.cmil.gestao.model.services.interfaces.IUsusarioDetailsService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
@Service
//@AllArgsConstructor
public class UsuarioDetailsServiceImpl implements IUsusarioDetailsService {

    @Autowired
    private IUsuarioRepository ur;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = ur.findByNome(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        //return UsuarioDetails.build(usuario);
        return new User(usuario.getEmail(), usuario.getPassword(), true, true, true, true, getAtuthorities(usuario.getPerfis()));

    }

    private Collection<? extends GrantedAuthority> getAtuthorities(Set<Perfil> perfis) {
        List<GrantedAuthority> authorities = perfis.stream()
                .map(role -> new SimpleGrantedAuthority(role.getTp().name()))
                .collect(Collectors.toList());

        return authorities;
    }

}

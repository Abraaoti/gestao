package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.model.dto.PerfilDTO;
import br.ind.cmil.gestao.model.dto.UsuarioDTO;
import br.ind.cmil.gestao.model.dto.mappers.UsuarioMapper;
import br.ind.cmil.gestao.model.entidades.Usuario;
import br.ind.cmil.gestao.model.repositorys.IPerfilRepository;
import br.ind.cmil.gestao.model.repositorys.IUsuarioRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author abraao
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final IUsuarioRepository ur;
    //@Autowired
   // private final IPerfilRepository iPerfilRepo;
    @Autowired
    private final UsuarioMapper um;

     public UsuarioDTO buscarPorEmailEAtivo(String email) {
        return ur.findByEmailAndAtivo(email).map(um::toDTO).orElseThrow(() -> new UsernameNotFoundException("Usuario " + email + " não encontrado."));
    }
    
    @Override
    @Transactional()
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

}

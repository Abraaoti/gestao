
package br.ind.cmil.gestao.model.security;

import br.ind.cmil.gestao.model.entidades.Perfil;
import br.ind.cmil.gestao.model.entidades.Usuario;
import br.ind.cmil.gestao.model.repositorys.IUsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class UserAuthenticationProvider  implements AuthenticationProvider {

    org.slf4j.Logger logger = LoggerFactory.getLogger(UserAuthenticationProvider.class);

    private IUsuarioRepository repository;

    private PasswordEncoder encoder;

    public UserAuthenticationProvider(IUsuarioRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    

    /**
     * Get the username and password from authentication object and validate with password encoders matching method
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Usuario usuario = repository.findByNome(username).get();
        if (usuario == null) {
            throw new BadCredentialsException("Details not found");
        }

        if (encoder.matches(password, usuario.getPassword())) {
            logger.info("Successfully Authenticated the user");
            return new UsernamePasswordAuthenticationToken(username, password, getUsuariosPeris(usuario.getPerfis()));
        } else {
            throw new BadCredentialsException("Password mismatch");
        }
    }

    /**
     * An user can have more than one roles separated by ",". We are splitting each role separately
     * @param perfil
     * @return
     */
    private List<GrantedAuthority> getUsuariosPeris(Set<Perfil> perfis) {
        
         List<GrantedAuthority> authorities = perfis.stream()
                .map(role -> new SimpleGrantedAuthority(role.getTp().name()))
                .collect(Collectors.toList());
        //List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        //String[] roles = perfil.split(",");
       // for (String role : roles) {
           // logger.info("Role: " + role);
           // grantedAuthorityList.add(new SimpleGrantedAuthority(role.replaceAll("\\s+", "")));
        //}

        return authorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

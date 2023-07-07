package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.model.entidades.Usuario;
import br.ind.cmil.gestao.model.repositorys.IUsuarioRepository;
import br.ind.cmil.gestao.model.security.jwt.data.UsuarioDetalhesimp;
import br.ind.cmil.gestao.model.services.interfaces.IUserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author abraao
 */
@Service
@RequiredArgsConstructor
public class UserServiceImp implements IUserService {

    private final IUsuarioRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
           Optional<Usuario> usuario = userRepository.findByNomeOrEmail(username, username);
           
                if (usuario.isEmpty()) {
                    throw new UsernameNotFoundException("Usuário [" + username + "] não encontrado");
                }

                return new UsuarioDetalhesimp(usuario.get());
              
    }
       
           
    

}
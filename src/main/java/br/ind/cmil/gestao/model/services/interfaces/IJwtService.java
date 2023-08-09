package br.ind.cmil.gestao.model.services.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author abraao
 */
public interface IJwtService {

    String getUsernameFromToken(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

}

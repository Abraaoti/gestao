package br.ind.cmil.gestao.model.services.interfaces;

import org.springframework.security.core.Authentication;

/**
 *
 * @author abraao
 */
public interface IJwtService {

    String parseToken(String token);

    String generateToken(Authentication authentication);

    void isTokenValid(String token);

}

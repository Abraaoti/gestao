package br.ind.cmil.gestao.services;

import org.springframework.security.core.Authentication;

/**
 *
 * @author abraao
 */
public interface JwtService {

    String parseToken(String token);

    String generateToken(Authentication authentication);

    void isTokenValid(String token);

}

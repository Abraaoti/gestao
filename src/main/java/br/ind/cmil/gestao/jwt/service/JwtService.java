package br.ind.cmil.gestao.jwt.service;

import org.springframework.security.core.Authentication;

/**
 *
 * @author ti
 */
public interface JwtService {

    String parseToken(String token);

    String generateToken(Authentication authentication);

    void isTokenValid(String token);
}

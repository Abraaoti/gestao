package br.ind.cmil.gestao.infra.securitys.token.service;

import org.springframework.security.core.Authentication;

/**
 *
 * @author ti
 */
public interface TokenService {

    String parseToken(String token);

    String generateToken(Authentication authentication);

    void isTokenValid(String token);
}

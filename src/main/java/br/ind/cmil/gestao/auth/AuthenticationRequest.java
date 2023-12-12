package br.ind.cmil.gestao.auth;

/**
 *
 * @author abraao
 */
public record AuthenticationRequest(
        String email,
        String password) {

}

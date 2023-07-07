package br.ind.cmil.gestao.security.controller;

/**
 *
 * @author abraao
 */
public record AuthenticationRequest(
        String email,
        String password) {

}

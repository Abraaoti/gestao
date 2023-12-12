package br.ind.cmil.gestao.response;

import java.util.Set;

/**
 *
 * @author abraao
 */
public record UsuarioResponse(
        Long id,
        String username,
        String email,
        Set<String> roles) {

}

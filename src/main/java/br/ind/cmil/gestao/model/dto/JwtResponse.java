package br.ind.cmil.gestao.model.dto;

import java.util.Set;

/**
 *
 * @author abraao
 */
public record JwtResponse(
        String token,
        String type,
        Long id,
        String nome,
        String email,
        Set<String> roles) {

}

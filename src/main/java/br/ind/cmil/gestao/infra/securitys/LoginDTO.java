package br.ind.cmil.gestao.infra.securitys;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author abraao
 */
public record LoginDTO(
        @NotBlank
        String login,
        @NotBlank
        String password) {

}

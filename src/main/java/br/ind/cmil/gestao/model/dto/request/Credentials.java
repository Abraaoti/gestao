package br.ind.cmil.gestao.model.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author abraao
 */
public record Credentials(
       
        @NotBlank
        String username,
        @NotBlank
        String password) {

}

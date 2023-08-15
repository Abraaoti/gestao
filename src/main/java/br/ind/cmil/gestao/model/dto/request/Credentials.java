package br.ind.cmil.gestao.model.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author abraao
 */
public record Credentials(
      
	//List<String> roles;
        @NotBlank
        String username,
        @NotBlank
        String password) {

}

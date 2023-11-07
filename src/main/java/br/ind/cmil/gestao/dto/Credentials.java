package br.ind.cmil.gestao.dto;

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

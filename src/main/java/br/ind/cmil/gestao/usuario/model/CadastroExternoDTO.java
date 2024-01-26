
package br.ind.cmil.gestao.usuario.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author ti
 */
public record CadastroExternoDTO(
       @JsonProperty
        Long id,
        @NotBlank
        @NotNull
        String nome,
        @NotBlank
        @NotNull
        String email,
        @NotBlank
        @NotNull
        String password) {

}


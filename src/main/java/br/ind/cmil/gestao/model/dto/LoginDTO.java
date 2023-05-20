package br.ind.cmil.gestao.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author abraao
 */
public record LoginDTO(
        @JsonProperty
        Long id,
        @NotBlank
        @NotNull
        String emailOUNome,
        @NotBlank
        @NotNull
        String password) {

}

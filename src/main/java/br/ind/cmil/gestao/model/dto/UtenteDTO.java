package br.ind.cmil.gestao.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author abraao
 */
public record UtenteDTO(
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

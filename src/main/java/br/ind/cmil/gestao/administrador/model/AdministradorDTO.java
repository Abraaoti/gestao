package br.ind.cmil.gestao.administrador.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author ti
 */
public record AdministradorDTO(
        @JsonProperty
        Long id,
        @NotBlank
        @NotNull
        String nome,
        @NotBlank
        @NotNull
        String usuario) {

}

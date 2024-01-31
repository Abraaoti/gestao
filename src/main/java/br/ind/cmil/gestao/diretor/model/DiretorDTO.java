package br.ind.cmil.gestao.diretor.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author ti
 */
public record DiretorDTO(
        @JsonProperty
        Long id,
        @NotBlank
        @NotNull
        String nome,
        @NotBlank
        @NotNull
        Long usuario) {

}
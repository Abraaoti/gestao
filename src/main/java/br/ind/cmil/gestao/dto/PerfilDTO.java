package br.ind.cmil.gestao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author abraao
 */
public record PerfilDTO(
        Long id,
        @NotBlank
        @NotNull
        String p) {

}

package br.ind.cmil.gestao.telefone.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author ti
 */
public record TelefoneDTO(
        Long id,
        @NotBlank
        @NotNull
        String numero,
        @NotBlank
        @NotNull
        String tipo,
        @NotBlank
        @NotNull
        String pessoa) {

}

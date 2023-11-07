package br.ind.cmil.gestao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author abraao
 */
public record TelefoneDTO(
        //@JsonProperty
        Long id,
        @NotBlank
        @NotNull
        String numero,
        @NotBlank
        @NotNull
        String tipo,
        @NotBlank
        @NotNull
        FuncionarioDTO pessoa) {

}

package br.ind.cmil.gestao.model.dto;

import br.ind.cmil.gestao.model.entidades.Pessoa;
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
        PessoaDTO pessoa) {

}

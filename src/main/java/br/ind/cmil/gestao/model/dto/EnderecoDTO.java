package br.ind.cmil.gestao.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author abraao
 */
public record EnderecoDTO(
       //@JsonProperty
        Long id,
        @NotBlank
        @NotNull
        String uf,
        String cidade,
        @NotBlank
        @NotNull
        String bairro,
        @NotBlank
        @NotNull
        String rua,
        @NotBlank
        @NotNull
        String cep,
        @NotBlank
        @NotNull
        String numero,
        @NotBlank
        @NotNull
        String complemento,
        @NotBlank
        @NotNull
        String pessoa
       ) {

}

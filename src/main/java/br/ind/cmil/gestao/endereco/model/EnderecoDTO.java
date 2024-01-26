package br.ind.cmil.gestao.endereco.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author ti
 */
public record EnderecoDTO(
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
        String pessoa) {

}

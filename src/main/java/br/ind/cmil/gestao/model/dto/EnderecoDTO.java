package br.ind.cmil.gestao.model.dto;

import br.ind.cmil.gestao.model.entidades.Pessoa;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author abraao
 */
public record EnderecoDTO(
        @JsonProperty
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
        Pessoa pessoa
       ) {

}
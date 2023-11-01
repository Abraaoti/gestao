package br.ind.cmil.gestao.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author abraao
 */
public record LotacaoDTO(
        @JsonProperty
        Long id,
        String nome) {

}

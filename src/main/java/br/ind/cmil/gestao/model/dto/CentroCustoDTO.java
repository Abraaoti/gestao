package br.ind.cmil.gestao.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author abraao
 */
public record CentroCustoDTO(
        Long id,
        @NotNull
        @Size(max = 255)
        String nome
        ) {

}

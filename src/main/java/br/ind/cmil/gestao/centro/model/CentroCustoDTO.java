package br.ind.cmil.gestao.centro.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author ti
 */
public record CentroCustoDTO(
        Long id,
        @NotNull
        @Size(max = 255)
        String nome) {

}

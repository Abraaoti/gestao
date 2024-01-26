package br.ind.cmil.gestao.departamento.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author ti
 */
public record DepartamentoDTO(
        Long id,
        @NotNull
        @Size(max = 255)
        String nome) {

}

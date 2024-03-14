package br.ind.cmil.gestao.pessoa.dto;

import jakarta.validation.constraints.NotNull;

/**
 *
 * @author abraaocalelessocassi
 */
public record PessoaFisicaDTO(
       
         Long id,
        // @Pattern(regexp = "(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2}){11,14}$", message = "cpf obrigatório")
        @NotNull
        String cpf,
        @NotNull
        String rg,
        String mae,
        String pai,
        @NotNull
        String genero,
        @NotNull
        String estado_civil,
        String naturalidade,
        @NotNull
        Long pessoa) {

}

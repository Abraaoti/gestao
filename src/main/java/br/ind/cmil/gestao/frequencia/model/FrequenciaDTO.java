package br.ind.cmil.gestao.frequencia.model;

import java.time.LocalDate;

/**
 *
 * @author abraaocalelessocassi
 */
public record FrequenciaDTO(
        Long id,
        LocalDate dataAtual,
        Long funcionario) {

}

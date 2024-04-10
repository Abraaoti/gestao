package br.ind.cmil.gestao.frequencia.model;

import java.time.LocalTime;

/**
 *
 * @author abraaocalelessocassi
 */
public record FrequenciaDTO(
        Long id,
        LocalTime horaAtual,
        Long funcionario) {

}

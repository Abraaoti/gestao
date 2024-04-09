package br.ind.cmil.gestao.frequencia.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author abraaocalelessocassi
 */
public record FrequenciaDTO(
        Long id,
        String status,
        LocalDate data,
        LocalTime entradaManha,
        LocalTime saidaManha,
        LocalTime entradaTarde,
        LocalTime saidaTarde,
        Long funcionario) {

}

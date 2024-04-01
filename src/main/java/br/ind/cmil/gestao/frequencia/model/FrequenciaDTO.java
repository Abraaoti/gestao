package br.ind.cmil.gestao.frequencia.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author abraaocalelessocassi
 */
public record FrequenciaDTO(
        Long id,
        String status,
        LocalDate data,
        LocalDateTime entradaManha,
        LocalDateTime saidaManha,
        LocalDateTime entradaTarde,
        LocalDateTime saidaTarde,
        LocalDateTime entradaExtra,
        LocalDateTime saidaExtra,
        List<Long> funcionarios) {

}

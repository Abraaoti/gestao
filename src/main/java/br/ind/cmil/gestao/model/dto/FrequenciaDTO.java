package br.ind.cmil.gestao.model.dto;

import java.time.LocalDate;
import java.util.Set;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author abraao
 */
public record FrequenciaDTO(
        Long id,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate data,
        String status,
        Set<Long> funcionarios) {

}

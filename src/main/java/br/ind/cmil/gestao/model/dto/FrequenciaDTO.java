package br.ind.cmil.gestao.model.dto;

import java.time.LocalDate;
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
        Long funcionario) {

}

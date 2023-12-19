package br.ind.cmil.gestao.model.dto;

import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author abraao
 */
public record FrequenciaResponseDTO(
        Long id,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate data,
        String status,
        List<FuncionarioDTO> funcionario) {

}

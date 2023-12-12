package br.ind.cmil.gestao.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author abraao
 */
public record FrequenciaFuncionarioDTO(
        Long id,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate data,
        @NotBlank
        @NotNull
        Long frequencia,
        @NotBlank
        @NotNull
        Long funcionario) {

}

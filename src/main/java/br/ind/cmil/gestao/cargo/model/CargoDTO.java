package br.ind.cmil.gestao.cargo.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author ti
 */
public record CargoDTO(
        Long id,
        @NotNull
        @Size(max = 255)
        String nome,
        @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
        BigDecimal salario) {

}

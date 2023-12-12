package br.ind.cmil.gestao.model.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author abraao
 */
public record FuncionarioDTO(
        Long id,
        @NotNull
        String nome,
        @NotNull
        String sobrenome,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate nascimento,
        // @Pattern(regexp = "(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2}){11,14}$", message = "cpf obrigatório")
        @NotNull
        String cpf,
        @NotNull
        String rg,
        String mae,
        String pai,
        String clt,
        @NotNull
        String genero,
        @NotNull
        String estado_civil,
        String naturalidade,
        LocalDate admissao,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate demissao,
        @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
        BigDecimal salario,
        @NotNull
        Long cargo,
        @NotNull
        Long departamento,
        @NotNull
        Long centroCusto) {

}

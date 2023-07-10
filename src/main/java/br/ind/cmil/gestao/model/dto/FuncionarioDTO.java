package br.ind.cmil.gestao.model.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author abraao
 */
public record FuncionarioDTO(
        //@JsonProperty
        Long id,
        @NotBlank
        @NotNull
        String nome,
        String sobrenome,
        @Temporal(TemporalType.DATE)
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date nascimento,
        @NotBlank
        @NotNull
       // @Pattern(regexp = "(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", message = "cpf obrigatório")
        String cpf,
        @NotBlank
        @NotNull
        String rg,
        @NotBlank
        @NotNull
        String mae,
        @NotBlank
        @NotNull
        String pai,
        String passaporte,
        @NotBlank
        @NotNull
        String genero,
        @NotBlank
        @NotNull
        String estado_civil,
        @NotBlank
        @NotNull
        String naturalidade,
       
        LocalDate admissao,
        @NotBlank
        @NotNull
        String matricula,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate demissao,
        @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
       // @NotBlank
       // @NotNull
        BigDecimal salario) {

}

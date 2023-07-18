package br.ind.cmil.gestao.model.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
        @NotNull(message = "NOME é um campo requerido")
        String nome,
        @NotBlank
        @NotNull(message = "SOBRENOME é um campo requerido")
        String sobrenome,
        @Temporal(TemporalType.DATE)
        @DateTimeFormat(pattern = "yyyy-MM-dd")

        Date nascimento,
        @NotBlank
        @NotNull(message = "CPF é um campo requerido")
        // @Pattern(regexp = "(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", message = "cpf obrigatório")
        String cpf,
        @NotBlank
        @NotNull(message = "RG é um campo requerido")
        String rg,
        @NotBlank
        @NotNull(message = "MÃE é um campo requerido")
        String mae,
        @NotBlank
        @NotNull(message = "PAI é um campo requerido")
        String pai,
        @NotBlank
        @NotNull(message = "PASSAPORTE é um campo requerido")
        String passaporte,
        @NotBlank
        @NotNull(message = "GENERO é um campo requerido")
        String genero,
        @NotBlank
        @NotNull(message = "ESTADO CIVIL é um campo requerido")
        String estado_civil,
        @NotBlank
        @NotNull(message = "NATURALIDADE é um campo requerido")
        String naturalidade,
        LocalDate admissao,
        @NotBlank
        @NotNull(message = "MATRÍCULA é um campo requerido")
        String matricula,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate demissao,
        DepartamentoDTO departamento,
        @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
        BigDecimal salario) {

}

package br.ind.cmil.gestao.dependente.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author ti
 */
public record DependenteDTO(
        @JsonProperty
        Long id,
        @NotBlank
        @NotNull
        String nome,
        String sobrenome,
        @Temporal(TemporalType.DATE)
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate nascimento,
        @NotBlank
        @NotNull
        //@Pattern(regexp = "(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", message = "cpf obrigatório")
        String cpf,
        @NotBlank
        @NotNull
        String rg,
        @NotBlank
        @NotNull
        String genero,
        @NotBlank
        @NotNull
        String estado_civil,
        @NotBlank
        @NotNull
        Long funcionario) {

}

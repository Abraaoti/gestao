package br.ind.cmil.gestao.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author abraao
 */
public record FornecedorDTO(
        @JsonProperty
        Long id,
        @NotBlank
        @NotNull
        String nome,
        String razao_social,
        @Temporal(TemporalType.DATE)
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date abertura,
        @NotBlank
        @NotNull
        @Pattern(regexp = "(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", message = "cpf obrigatório")
        String cpf_cnpj,
        @NotBlank
        @NotNull
        String ie,
        @NotBlank
        @NotNull
        String im) {

}

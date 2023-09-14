package br.ind.cmil.gestao.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author abraao
 */
public record RegistrarUsuario(
        @JsonProperty
        Long id,
        @NotBlank
        @NotNull
        String nome,
        @NotBlank
        @NotNull
        String email,
        @NotBlank
        @NotNull
        String password,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDateTime dataCadastro,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDateTime updatedAt,
        Boolean ativo,
        String verificador,
        @NotBlank
        @NotNull
        Set<String> perfis) {


}

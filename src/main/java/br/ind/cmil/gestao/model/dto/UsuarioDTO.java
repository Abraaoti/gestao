package br.ind.cmil.gestao.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author abraao
 */
public record UsuarioDTO(
        @JsonProperty
        Long id,
        @NotBlank
        @NotNull
        String email,
        @NotBlank
        @NotNull
        String password,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDateTime dataCadastro,
        boolean ativo,
        String verificador,       
        List<PerfilDTO> perfis) {

}

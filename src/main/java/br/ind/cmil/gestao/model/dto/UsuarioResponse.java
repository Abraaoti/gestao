package br.ind.cmil.gestao.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author abraao
 */
public record UsuarioResponse(        
        @NotBlank
        @NotNull
        String nome,
        @NotBlank
        @NotNull
        String email,       
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @CreatedDate
        LocalDateTime dataCadastro,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @LastModifiedDate
        LocalDateTime updatedAt,
        Boolean ativo,
        String verificador,
        @NotBlank
        @NotNull
        List<String> perfis) {

}

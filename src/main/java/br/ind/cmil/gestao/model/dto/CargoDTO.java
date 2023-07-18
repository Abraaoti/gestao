package br.ind.cmil.gestao.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author abraao
 */
public record CargoDTO(
        @JsonProperty
        Long id,
        @NotBlank
        @NotNull
        @NotEmpty(message = "Um nome precisa ser inserido")
        String nome) {

  
    
    

}

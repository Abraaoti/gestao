
package br.ind.cmil.gestao.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author abraao
 */
public record AuxiliarAdministrativoDTO(
         @JsonProperty
        Long id,
        @NotBlank
        @NotNull
        String nome,
        RegistrarUsuario usuario
        
        ) {

}

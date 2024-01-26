
package br.ind.cmil.gestao.perfil.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author ti
 */
public record PerfilDTO(
         Long id,
        @NotBlank
        @NotNull
        String p
        ) {

}

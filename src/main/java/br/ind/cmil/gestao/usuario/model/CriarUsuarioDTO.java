package br.ind.cmil.gestao.usuario.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author ti
 */
public record CriarUsuarioDTO(
        @NotBlank
        @NotNull
        String nome,
        @NotBlank
        @NotNull
        @Email
        String email,
        @NotBlank
        @NotNull
        String password,
        @NotBlank
        @NotNull
        Boolean ativo,
        @NotBlank
        @NotNull
        List<String> perfis) {

}

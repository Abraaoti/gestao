package br.ind.cmil.gestao.model.dto;

/**
 *
 * @author abraao
 */
public record ResetPasswordData(
        String email,
        String token,
        String password,
        String repeatPassword
        ) {

}

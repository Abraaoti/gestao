package br.ind.cmil.gestao.dto;

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

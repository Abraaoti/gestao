package br.ind.cmil.gestao.exceptions;

/**
 *
 * @author abraao
 */
public class TokenException extends RuntimeException {

    private final String token;

    public TokenException(String token, String message) {
        super(message);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

   

}

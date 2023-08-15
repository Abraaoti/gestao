package br.ind.cmil.gestao.exceptions;

/**
 *
 * @author abraao
 */
public class InvalidUserCredentialsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidUserCredentialsException(String msg) {
        super(msg);
    }

}

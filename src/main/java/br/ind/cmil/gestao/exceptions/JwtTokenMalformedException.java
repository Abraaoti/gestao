package br.ind.cmil.gestao.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author abraao
 */
public class JwtTokenMalformedException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public JwtTokenMalformedException(String msg) {
        super(msg);
    }
}

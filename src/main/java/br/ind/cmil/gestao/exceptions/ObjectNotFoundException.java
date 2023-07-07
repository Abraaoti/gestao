
package br.ind.cmil.gestao.exceptions;

/**
 *
 * @author abraao
 */
public class ObjectNotFoundException  extends RuntimeException{

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
}

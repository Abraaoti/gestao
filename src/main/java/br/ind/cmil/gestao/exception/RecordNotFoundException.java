
package br.ind.cmil.gestao.exception;

/**
 *
 * @author abraao
 */
public class RecordNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RecordNotFoundException(Long id) {
        super("Registro não encontrado com o id: " + id);
    }
}

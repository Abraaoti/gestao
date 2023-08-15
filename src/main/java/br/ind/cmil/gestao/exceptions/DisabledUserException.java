
package br.ind.cmil.gestao.exceptions;

/**
 *
 * @author abraao
 */
public class DisabledUserException  extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DisabledUserException(String msg) {
		super(msg);
	}
}

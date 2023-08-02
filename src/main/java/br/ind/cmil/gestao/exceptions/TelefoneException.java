package br.ind.cmil.gestao.exceptions;

/**
 *
 * @author abraao
 */
public class TelefoneException extends RuntimeException {

    private final String nome;

    public TelefoneException(String nome, String message) {
        super(message);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

   

}

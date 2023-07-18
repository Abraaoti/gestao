package br.ind.cmil.gestao.exceptions;

/**
 *
 * @author abraao
 */
public class EnderecoException extends RuntimeException {

    private final String uf;

    public EnderecoException(String nome, String message) {
        super(message);
        this.uf = nome;
    }

    public String getUf() {
        return uf;
    }

   

}

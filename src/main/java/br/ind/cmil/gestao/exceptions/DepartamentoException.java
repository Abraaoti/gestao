package br.ind.cmil.gestao.exceptions;

/**
 *
 * @author abraao
 */
public class DepartamentoException extends RuntimeException {

    private final String nome;

    public DepartamentoException(String nome, String message) {
        super(message);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

   

}

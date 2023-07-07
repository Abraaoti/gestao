package br.ind.cmil.gestao.exceptions;

/**
 *
 * @author abraao
 */
public class AcessoNegadoException extends RuntimeException {

    private final String nome;

    public AcessoNegadoException(String nome, String message) {
        super(message);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

   

}

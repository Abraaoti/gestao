package br.ind.cmil.gestao.exceptions;

/**
 *
 * @author abraao
 */
public class UsuarioNotFoundException extends RuntimeException {

    private final String nome;

    public UsuarioNotFoundException(String nome, String message) {
        super(message);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

   

}

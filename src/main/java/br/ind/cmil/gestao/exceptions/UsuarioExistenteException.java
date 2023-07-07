package br.ind.cmil.gestao.exceptions;

/**
 *
 * @author abraao
 */
public class UsuarioExistenteException extends RuntimeException {

    private final String nome;

    public UsuarioExistenteException(String nome, String message) {
        super(message);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

   

}

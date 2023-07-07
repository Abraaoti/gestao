package br.ind.cmil.gestao.exceptions;

/**
 *
 * @author abraao
 */
public class FuncionarioException extends RuntimeException {

    private final String perfil;

    public FuncionarioException(String nome, String message) {
        super(message);
        this.perfil = nome;
    }

    public String getPerfil() {
        return perfil;
    }

   

}

package br.ind.cmil.gestao.exceptions;

/**
 *
 * @author abraao
 */
public class PerfilExistenteException extends RuntimeException {

    private final String perfil;

    public PerfilExistenteException(String nome, String message) {
        super(message);
        this.perfil = nome;
    }

    public String getPerfil() {
        return perfil;
    }

   

}

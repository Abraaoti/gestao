
package br.ind.cmil.gestao.model.entidades;

/**
 *
 * @author abraao
 */
public class Empresa {
    private String endereco;
    private String cidade;
    private String telefone;
    private String email;
    private boolean matriz;

    /**
     * Indica qual funcionário é o gerente da empresa.
    */
    private Funcionario gerente;
}

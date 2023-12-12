package br.ind.cmil.gestao.base;

import br.ind.cmil.gestao.services.IPessoa;

/**
 *
 * @author abraao
 */
public abstract class PessoaFactory {

    public abstract IPessoa GetPessoa(String vehicle);
}

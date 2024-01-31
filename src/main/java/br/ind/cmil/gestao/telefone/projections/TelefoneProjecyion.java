package br.ind.cmil.gestao.telefone.projections;

import br.ind.cmil.gestao.pessoa.domain.Pessoa;

/**
 *
 * @author ti
 */
public interface TelefoneProjecyion {

    Long getId();

    String getNumero();

    String getTipo();

    Pessoa getPessoa();

}

package br.ind.cmil.gestao.endereco.repository.projections;

import br.ind.cmil.gestao.pessoa.domain.Pessoa;

/**
 *
 * @author abraaocalelessocassi
 */
public interface EnderecoProjection {

    public Long getId();

    String getUf();

    String getCidade();

    String getBairro();

    String getRua();

    String getCep();

    String getNumero();

    String getComplemento();

    Pessoa getPessoa();

}

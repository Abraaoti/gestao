package br.ind.cmil.gestao.funcionario.repository.projections;

import br.ind.cmil.gestao.cargo.domain.Cargo;
import br.ind.cmil.gestao.departamento.domain.Departamento;
import br.ind.cmil.gestao.pessoa.domain.PessoaFisica;
import br.ind.cmil.gestao.pessoa.domain.PessoaJuridica;

/**
 *
 * @author ti
 */
public interface FuncionarioBaseProjection {

    Long getId();

    String getNome();

    String getSobrenome();

    String getNascimento();

    String getCpf();

    String getGg();

    String getMae();

    String getPai();

    String getGenero();

    String getEstado_civil();

    String getNaturalidade();

    String getClt();

    String getAdmissao();

    PessoaFisica getPessoaFisica();

    Departamento getDepartamento();

    Cargo getCargo();

    String getDemissao();

    PessoaJuridica getEmpresa();

}

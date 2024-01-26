package br.ind.cmil.gestao.repositorys.projection;

import br.ind.cmil.gestao.cargo.domain.Cargo;
import br.ind.cmil.gestao.departamento.domain.Departamento;

/**
 *
 * @author abraao
 */
public interface HistoricoFuncionario {

    Long getId();

    String getNome();

    String getCpf();

    Departamento getDepartamento();

    Cargo getCargo();

}

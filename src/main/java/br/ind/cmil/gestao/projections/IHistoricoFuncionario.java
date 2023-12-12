package br.ind.cmil.gestao.projections;

import br.ind.cmil.gestao.domain.AuxiliarAdministrativo;
import br.ind.cmil.gestao.domain.Funcionario;

/**
 *
 * @author abraao
 */
public interface IHistoricoFuncionario {

    Long getId();

    String getDtaPresenca();

    AuxiliarAdministrativo getAuxiliarAdministrativo();

    Funcionario getFuncionario();

    String getStatus();
}

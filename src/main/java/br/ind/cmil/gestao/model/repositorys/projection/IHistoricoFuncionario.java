package br.ind.cmil.gestao.model.repositorys.projection;

import br.ind.cmil.gestao.model.entidades.AuxiliarAdministrativo;
import br.ind.cmil.gestao.model.entidades.Funcionario;

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

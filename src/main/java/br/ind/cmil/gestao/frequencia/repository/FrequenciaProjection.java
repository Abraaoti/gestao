package br.ind.cmil.gestao.frequencia.repository;

import br.ind.cmil.gestao.enums.TipoFrequencia;
import br.ind.cmil.gestao.funcionario.domain.Funcionario;

/**
 *
 * @author abraaocalelessocassi
 */
public interface FrequenciaProjection {

    Long getId();

    String getData();

    TipoFrequencia getStatus();

    String getEntradaManha();

    String getSaidaManha();

    String getEntradaTarde();

    String getSaidaTarde();

    String getEntradaExtra();

    String getSaidaExtra();

    Funcionario getFuncionario();
}

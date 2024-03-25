
package br.ind.cmil.gestao.repositorys.projections;

import br.ind.cmil.gestao.frequencia.domain.Frequencia;
import br.ind.cmil.gestao.funcionario.domain.Funcionario;

/**
 *
 * @author ti
 */
public interface FuncionarioFrequencia {
    Long getId();
    String getData();
    Funcionario getFuncionario();
    Frequencia getFrequencia();
    
}

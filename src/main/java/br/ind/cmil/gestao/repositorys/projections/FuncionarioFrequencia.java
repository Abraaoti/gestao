
package br.ind.cmil.gestao.repositorys.projections;

import br.ind.cmil.gestao.domain.Frequencia;
import br.ind.cmil.gestao.domain.Funcionario;

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

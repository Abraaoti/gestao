
package br.ind.cmil.gestao.repositorys.projections;

import br.ind.cmil.gestao.domain.Cargo;

/**
 *
 * @author ti
 */
public interface HistoricoFuncionario {
    Long getId();
    String getNome();
    String getCpf();
    Cargo getCargo();
    
}
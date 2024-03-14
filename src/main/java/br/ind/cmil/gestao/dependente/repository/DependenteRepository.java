
package br.ind.cmil.gestao.dependente.repository;

import br.ind.cmil.gestao.dependente.domain.Dependente;
import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ti
 */
public interface DependenteRepository  extends JpaRepository<Dependente, Long> {

    Dependente findFirstByFuncionario(Funcionario funcionario);
    
}
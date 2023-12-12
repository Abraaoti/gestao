
package br.ind.cmil.gestao.repositorys;

import br.ind.cmil.gestao.domain.Dependente;
import br.ind.cmil.gestao.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author abraao
 */
public interface DependenteRepository  extends JpaRepository<Dependente, Long> {

    Dependente findFirstByFuncionario(Funcionario funcionario);

    boolean existsByNomeIgnoreCase(String nome);
    
}

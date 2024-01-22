
package br.ind.cmil.gestao.repositorys;

import br.ind.cmil.gestao.domain.FuncionarioFrequencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ti
 */
@Repository
public interface FuncionarioFrequenciaRepository  extends JpaRepository<FuncionarioFrequencia, Long>{
   
    @Query("SELECT obj FROM FuncionarioFrequencia obj  where obj.funcionario.nome like :search%")
    Page<FuncionarioFrequencia> findByFuncionarioAndFrequencia(String search, Pageable pageable);
}

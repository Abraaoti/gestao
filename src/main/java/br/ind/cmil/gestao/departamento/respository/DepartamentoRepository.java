
package br.ind.cmil.gestao.departamento.respository;

import br.ind.cmil.gestao.departamento.domain.Departamento;
import java.util.Optional;
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
public interface DepartamentoRepository extends JpaRepository<Departamento, Long>{

   
    @Query("SELECT d FROM Departamento d where d.nome like :search%")
    Page<Departamento> searchAll(String search, Pageable pageable);

    Optional<Departamento> findByNome(String nome);

}

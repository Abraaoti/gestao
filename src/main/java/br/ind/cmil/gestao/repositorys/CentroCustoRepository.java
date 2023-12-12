
package br.ind.cmil.gestao.repositorys;

import br.ind.cmil.gestao.domain.CentroCusto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author abraao
 */
@Repository
public interface CentroCustoRepository extends JpaRepository<CentroCusto, Long> {

    @Query(value = "SELECT obj FROM CentroCusto obj ")
    List<CentroCusto> searchAll();

    @Query("select l from CentroCusto l where l.nome like :search%")
    Page<CentroCusto> searchAll(String search, Pageable pageable);

    Optional<CentroCusto> findByNome(String contrato);

    List<CentroCusto> findAllByOrderByNomeAscIdDesc();

     boolean existsByNomeIgnoreCase(String nome);
    
    
}

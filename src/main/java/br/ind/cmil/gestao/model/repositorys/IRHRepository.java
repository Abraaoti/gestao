
package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.RH;
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
public interface IRHRepository extends JpaRepository<RH, Long> {

    @Query(value = "SELECT obj FROM RH obj ")
    List<RH> searchAll();

    @Query(value = "SELECT obj FROM RH obj ",
            countQuery = "SELECT COUNT(obj) FROM RH obj ")
    Page<RH> searchAll(Pageable pageable);

    Optional<RH> findByNome(String nome);
    
}

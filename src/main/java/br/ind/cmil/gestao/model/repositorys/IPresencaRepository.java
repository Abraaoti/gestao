
package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.Presenca;
import br.ind.cmil.gestao.model.enums.TipoPresenca;
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
public interface IPresencaRepository extends JpaRepository<Presenca, Long> {

    @Query("select p from Presenca p where p.status like :status%")
    Page<?> searchAll(TipoPresenca status, Pageable pageable);
    
}

package br.ind.cmil.gestao.repositorys;

import br.ind.cmil.gestao.domain.Presenca;
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
public interface PresencaRepository extends JpaRepository<Presenca, Long> {

    @Query("select p from Presenca p  where p.status  like :status%")
    Page<Presenca> searchAll(String status, Pageable pageable);

    public Optional<Presenca> findByStatus(String status);

}

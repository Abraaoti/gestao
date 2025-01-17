
package br.ind.cmil.gestao.cargo.repository;

import br.ind.cmil.gestao.cargo.domain.Cargo;
import java.util.List;
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
public interface CargoRepository extends JpaRepository<Cargo, Long> {

    @Query("SELECT c FROM Cargo c  where c.nome like :search%")
    Page<Cargo> searchAll(String search, Pageable pageable);

    Optional<Cargo> findByNome(String nome);

}


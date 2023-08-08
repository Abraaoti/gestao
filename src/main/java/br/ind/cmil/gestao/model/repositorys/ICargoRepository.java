package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.Cargo;
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
public interface ICargoRepository extends JpaRepository<Cargo, Long> {

    @Query(value = "SELECT obj FROM Cargo obj ")
    List<Cargo> searchAll();

    @Query(value = "SELECT obj FROM Cargo obj ",
            countQuery = "SELECT COUNT(obj) FROM Cargo obj ")
    Page<Cargo> searchAll(Pageable pageable);

    Optional<Cargo> findByNome(String nome);

}

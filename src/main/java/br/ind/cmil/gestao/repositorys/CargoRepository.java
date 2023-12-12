package br.ind.cmil.gestao.repositorys;

import br.ind.cmil.gestao.domain.Cargo;
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
public interface CargoRepository extends JpaRepository<Cargo, Long> {

    @Query(value = "SELECT obj FROM Cargo obj ")
    List<Cargo> searchAll();

    @Query(value = "SELECT obj FROM Cargo obj ",
            countQuery = "SELECT COUNT(obj) FROM Cargo obj ")
    Page<Cargo> searchAll(Pageable pageable);
    @Query("SELECT obj FROM Cargo obj  where obj.nome like :search%")
    Page<Cargo> searchAll(String search, Pageable pageable);

    Optional<Cargo> findByNome(String nome);

}

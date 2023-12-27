package br.ind.cmil.gestao.repositorys;

import br.ind.cmil.gestao.domain.Cargo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author abraao
 */
@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long>, JpaSpecificationExecutor<Cargo>{

    @Query(value = "SELECT c FROM Cargo c ")
    List<Cargo> searchAll();

    @Query(value = "FROM Cargo c  ")
    Page<Cargo> searchAll(Pageable pageable);
    
    @Query("FROM Cargo c  where c.nome like :search%")
    Page<Cargo> searchAll(String search, Pageable pageable);

    Optional<Cargo> findByNome(String nome);

}

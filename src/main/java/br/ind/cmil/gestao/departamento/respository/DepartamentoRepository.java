
package br.ind.cmil.gestao.departamento.respository;

import br.ind.cmil.gestao.departamento.domain.Departamento;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ti
 */
@Transactional
@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long>{

    @Query("SELECT obj FROM Departamento obj ")
    List<Departamento> searchAll();

    @Query("SELECT d  FROM Departamento d  ")
    Page<Departamento> searchAll(Pageable pageable);

    @Query("SELECT d FROM Departamento d where d.nome like :search%")
    Page<Departamento> searchAll(String search, Pageable pageable);

    Optional<Departamento> findByNome(String nome);

}

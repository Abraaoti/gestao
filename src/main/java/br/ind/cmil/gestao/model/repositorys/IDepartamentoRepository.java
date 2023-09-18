package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.Departamento;
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
public interface IDepartamentoRepository extends JpaRepository<Departamento, Long> {

    @Query(value = "SELECT obj FROM Departamento obj ")
    List<Departamento> searchAll();

    @Query(value = "SELECT obj FROM Departamento obj ",
            countQuery = "SELECT COUNT(obj) FROM Departamento obj ")
    Page<Departamento> searchAll(Pageable pageable);

    @Query("SELECT obj FROM Departamento obj where obj.nome =: search")
    Page<?> searchAll(String search, Pageable pageable);

    Optional<Departamento> findByNome(String nome);

}

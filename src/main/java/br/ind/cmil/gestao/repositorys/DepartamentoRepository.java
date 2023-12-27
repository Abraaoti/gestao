package br.ind.cmil.gestao.repositorys;

import br.ind.cmil.gestao.domain.Departamento;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
@Transactional
@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long>, JpaSpecificationExecutor<Departamento>{

    @Query(value = "SELECT obj FROM Departamento obj ")
    List<Departamento> searchAll();

    @Query(value = " FROM Departamento d  ")
    Page<Departamento> searchAll(Pageable pageable);

    @Query(value = "FROM Departamento d where d.nome like :search%")
    Page<Departamento> searchAll(String search, Pageable pageable);

    Optional<Departamento> findByNome(String nome);

}

package br.ind.cmil.gestao.repositorys;

import br.ind.cmil.gestao.domain.Frequencia;
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
public interface FrequenciaRepository extends JpaRepository<Frequencia, Long> {

    @Query("select f from Frequencia f  JOIN funcionario where f.status  like :status%")
    Page<Frequencia> searchAll(String status, Pageable pageable);

}

package br.ind.cmil.gestao.repositorys;

import br.ind.cmil.gestao.domain.Frequencia;
import br.ind.cmil.gestao.enums.TipoFrequencia;
import java.util.List;
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

    @Query(value = "SELECT DISTINCT obj FROM Frequencia obj  JOIN  FETCH obj.funcionarios where obj.status  like :status%")
    Page<Frequencia> searchAll(TipoFrequencia status, Pageable pageable);
    
    @Query("SELECT  obj FROM Frequencia obj  JOIN  FETCH obj.funcionarios where obj IN:frequencias")
    List<Frequencia> findAllFrequencias(List<Frequencia> frequencias);

}

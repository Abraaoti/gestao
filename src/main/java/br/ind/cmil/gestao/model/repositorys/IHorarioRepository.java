
package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.Horario;
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
public interface IHorarioRepository extends JpaRepository<Horario, Long> {

    @Query(value = "SELECT obj FROM Horario obj ")
    List<Horario> searchAll();

    @Query(value = "SELECT obj FROM Horario obj ",
            countQuery = "SELECT COUNT(obj) FROM Horario obj ")
    Page<Horario> searchAll(Pageable pageable);
    
}

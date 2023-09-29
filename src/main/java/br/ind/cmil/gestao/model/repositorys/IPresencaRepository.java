package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.Horario;
import br.ind.cmil.gestao.model.entidades.Presenca;
import br.ind.cmil.gestao.model.enums.TipoPresenca;
import br.ind.cmil.gestao.model.repositorys.projection.IHistoricoFuncionario;
import java.time.LocalDate;
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
public interface IPresencaRepository extends JpaRepository<Presenca, Long> {

    @Query("select h "
            + "from Horario h "
            + "where not exists("
            + "select p.horario.id "
            + "from Presenca p "
            + "where "
            + "p.auxiliar.id = :id and "
            + "p.dataPresenca = :data and "
            + "p.horario.id = h.id "
            + ") "
            + "order by h.horaMinuto asc")
    List<Horario> findByAuxiliarIdAndDataNotHorarioPresente(Long id, LocalDate data);

    @Query("select p from Presenca p "
            + "where "
            + "	(p.id = :id AND p.auxiliar.usuario.email like :email) ")
    //+ " OR "
    //+ " (a.id = :id AND a.medico.usuario.email like :email)")
    Optional<Presenca> findByIdAndFuncionarioEmail(Long id, String email);

    @Query("select p from Presenca p where p.status like :status%")
    Page<Presenca> searchAll(TipoPresenca status, Pageable pageable);

}

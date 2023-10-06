package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.CartaoPonto;
import br.ind.cmil.gestao.model.entidades.Horario;
import br.ind.cmil.gestao.model.entidades.Presenca;
import br.ind.cmil.gestao.model.enums.EPeriodo;
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
public interface ICartaoPontoRepository extends JpaRepository<CartaoPonto, Long> {

    @Query("select h "
            + "from Horario h "
            + "where not exists("
            + "select c.horario.id "
            + "from CartaoPonto c "
            + "where "
            + "c.auxiliar.id = :id and "
            + "c.dia = :data and "
            + "c.horario.id = h.id "
            + ") "
            + "order by h.horaMinuto asc")
    List<Horario> findByAuxiliarIdAndDataNotHorarioPresente(Long id, LocalDate data);

    @Query("select c from CartaoPonto c "
            + "where "
            + "	(c.id = :id AND c.auxiliar.usuario.email like :email) ")
    //+ " OR "
    //+ " (a.id = :id AND a.medico.usuario.email like :email)")
    Optional<CartaoPonto> findByIdAndFuncionarioEmail(Long id, String email);

    @Query("select c from Presenca c where c.status like :p%")
    Page<CartaoPonto> searchAll(EPeriodo p, Pageable pageable);

}

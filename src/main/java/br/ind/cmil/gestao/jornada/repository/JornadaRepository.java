package br.ind.cmil.gestao.jornada.repository;

import br.ind.cmil.gestao.jornada.domain.JornadaTrabalho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author abraaocalelessocassi
 */
@Repository
public interface JornadaRepository extends JpaRepository<JornadaTrabalho, Long> {
}

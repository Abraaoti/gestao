package br.ind.cmil.gestao.jornada.service;

import br.ind.cmil.gestao.jornada.domain.JornadaTrabalho;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author abraaocalelessocassi
 */
public interface JornadaService {

    JornadaTrabalho saveJornada(JornadaTrabalho jornadaTrabalho);

    List<JornadaTrabalho> findAll();

    Optional<JornadaTrabalho> getById(Long idJornada);

    JornadaTrabalho updateJornada(JornadaTrabalho jornadaTrabalho);

    void deleteJornada(Long idJornada);
}

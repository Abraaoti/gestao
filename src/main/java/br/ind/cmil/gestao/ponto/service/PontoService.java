package br.ind.cmil.gestao.ponto.service;

import br.ind.cmil.gestao.ponto.model.PontoDTO;
import java.util.List;

/**
 *
 * @author ti
 */
public interface PontoService {

    List<PontoDTO> getPontos();

    PontoDTO buscarPorId(final Long id);

    Long create(final PontoDTO pontoDTO);

    void update(final Long id, final PontoDTO pontoDTO);

    void delete(final Long id);

}

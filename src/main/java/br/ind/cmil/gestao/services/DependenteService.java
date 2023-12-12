package br.ind.cmil.gestao.services;

import br.ind.cmil.gestao.model.dto.DependenteDTO;
import java.util.List;

/**
 *
 * @author abraao
 */
public interface DependenteService {

    List<DependenteDTO> findAll();

    DependenteDTO get(Long id);

    Long create(DependenteDTO dependenteDTO);

    void delete(Long id);

    boolean nomeExists(String nome);

}

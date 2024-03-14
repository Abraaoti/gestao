
package br.ind.cmil.gestao.dependente.services;

import br.ind.cmil.gestao.dependente.model.DependenteDTO;
import java.util.List;

/**
 *
 * @author ti
 */
public interface DependenteService {
    
    List<DependenteDTO> findAll();

    DependenteDTO get(Long id);

    Long create(DependenteDTO dependenteDTO);

    void delete(Long id);
}

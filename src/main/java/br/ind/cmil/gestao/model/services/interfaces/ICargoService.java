package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.CargoDTO;
import br.ind.cmil.gestao.model.entidades.Cargo;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author abraao
 */
public interface ICargoService {

    List<CargoDTO> list(Pageable pageable);

    Set<CargoDTO> lista();

    CargoDTO findById(Long id);

    CargoDTO create(CargoDTO c);

    void delete(Long id);

    Cargo findByNome(String nome);

}

package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.CargoDTO;
import br.ind.cmil.gestao.model.entidades.Cargo;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author abraao
 */
public interface ICargoService {

    List<CargoDTO> list(Pageable pageable);

    Set<CargoDTO> lista();

    Map<String, Object> buscarTodos(HttpServletRequest request);

    CargoDTO findById(Long id);

    CargoDTO create(CargoDTO c);

    void delete(Long id);

    Cargo findByNome(String nome);

}

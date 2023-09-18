package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.DepartamentoDTO;
import br.ind.cmil.gestao.model.entidades.Departamento;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author abraao
 */
public interface IDepartamentoService {

    List<DepartamentoDTO> list(Pageable pageable);

    Set<DepartamentoDTO> lista();

    DepartamentoDTO findById(Long id);

    DepartamentoDTO create(DepartamentoDTO dep);

    DepartamentoDTO update(DepartamentoDTO dep);

    Map<String, Object> buscarTodos(HttpServletRequest request);

    void delete(Long id);

    Departamento findByNome(String nome);

}

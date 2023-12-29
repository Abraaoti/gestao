package br.ind.cmil.gestao.services;

import br.ind.cmil.gestao.model.dto.DepartamentoDTO;
import br.ind.cmil.gestao.domain.Departamento;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *
 * @author abraao
 */
public interface DepartamentoService {

    Long salvar(DepartamentoDTO d);

    List<DepartamentoDTO> lista();

    DepartamentoDTO findById(Long id);

    String  getReferencedWarning(Long id);

    Map<Long, String> departamentos();

    Map<String, Object> buscarTodos(HttpServletRequest request);

    void delete(Long id);

    Departamento findByNome(String nome);

}

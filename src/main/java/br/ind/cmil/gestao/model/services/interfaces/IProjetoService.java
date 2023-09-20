package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.ProjetoDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author abraao
 */
public interface IProjetoService {

    Set<ProjetoDTO> perfis();

    ProjetoDTO findById(Long id);

    ProjetoDTO create(ProjetoDTO p);

    Map<String, Object> buscarTodos(HttpServletRequest request);

    void delete(Long id);

    // Perfil getOrCreate(String name);
}

package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.LotacaoDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *
 * @author abraao
 */
public interface LotacaoService {

    void save(LotacaoDTO lotacao);

    List<LotacaoDTO> lista();

    LotacaoDTO findById(Long id);

    void delete(Long id);

    Map<String, Object> buscarTodos(HttpServletRequest request);
}

package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.PresencaDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 * @author abraao
 */
public interface IPresencaService {

    PresencaDTO findById(Long id);

    void create(PresencaDTO p);

    Map<String, Object> presencas(HttpServletRequest request);

    void delete(Long id);
}

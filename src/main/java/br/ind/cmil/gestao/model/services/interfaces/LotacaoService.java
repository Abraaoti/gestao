package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.entidades.Lotacao;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *
 * @author abraao
 */
public interface LotacaoService {

    void salvar(Lotacao lotacao);

    List<Lotacao> lista();

    Lotacao findById(Long id);

    void delete(Long id);

    Map<String, Object> buscarTodos(HttpServletRequest request);

    Lotacao findByNome(String unigal);
}

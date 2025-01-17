package br.ind.cmil.gestao.centro.service;

import br.ind.cmil.gestao.centro.domain.CentroCusto;
import br.ind.cmil.gestao.centro.model.CentroCustoDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ti
 */
public interface CentroCustoService {

    List<CentroCustoDTO> findAll();

    CentroCustoDTO get(Long id);

    Long create(CentroCustoDTO centroCustoDTO);

    void delete(Long id);

    boolean nomeExists(String nome);

    Map<String, Object> buscarTodos(HttpServletRequest request);

    CentroCusto findByNome(String centro);

}

package br.ind.cmil.gestao.services;

import br.ind.cmil.gestao.model.dto.CentroCustoDTO;
import br.ind.cmil.gestao.domain.CentroCusto;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *
 * @author abraao
 */
public interface CentroCustoService {

    List<CentroCustoDTO> findAll();

    CentroCustoDTO get( Long id);

    Long create( CentroCustoDTO centroCustoDTO);

   // void update(final Long id, final CentroCustoDTO centroCustoDTO);

    void delete( Long id);

    boolean nomeExists( String nome);

    String getReferencedWarning( Long id);


    Map<String, Object> buscarTodos(HttpServletRequest request);

    CentroCusto findByNome(String centro);
}

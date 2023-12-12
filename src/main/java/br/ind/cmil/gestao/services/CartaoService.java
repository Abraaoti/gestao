package br.ind.cmil.gestao.services;

import br.ind.cmil.gestao.domain.Cargo;
import br.ind.cmil.gestao.domain.Cartao;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *
 * @author abraao
 */
public interface CartaoService {

    void salvar(Cartao c);

    Cartao findById(Long id);

    Cartao findByNumero(String numero);

    List<Cartao> lista();

    Map<String, Object> buscarTodos(HttpServletRequest request);

    void delete(Long id);

}

package br.ind.cmil.gestao.services;

import br.ind.cmil.gestao.domain.Cargo;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *
 * @author abraao
 */
public interface CargoService {

    void salvar(Cargo c);

    Map<Long, String> cargos();

    Cargo findById(Long id);

    List<Cargo> lista();

    void delete(Long id);

    Cargo findByNome(String nome);

    Map<String, Object> buscarTodos(HttpServletRequest request);

}
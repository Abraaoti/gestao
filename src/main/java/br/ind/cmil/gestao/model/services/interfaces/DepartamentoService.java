package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.entidades.Departamento;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *
 * @author abraao
 */
public interface DepartamentoService {

    void salvar(Departamento d);

    List<Departamento> lista();

    Departamento findById(Long id);

    Map<String, Object> buscarTodos(HttpServletRequest request);

    void delete(Long id);

    Departamento findByNome(String nome);

}

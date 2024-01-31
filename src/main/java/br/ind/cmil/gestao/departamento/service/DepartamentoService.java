package br.ind.cmil.gestao.departamento.service;

import br.ind.cmil.gestao.departamento.domain.Departamento;
import br.ind.cmil.gestao.departamento.model.DepartamentoDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ti
 */
public interface DepartamentoService {

    Long salvar(DepartamentoDTO d);

    List<DepartamentoDTO> lista();

    DepartamentoDTO findById(Long id);

    Map<Long, String> departamentos();

    Map<String, Object> buscarTodos(HttpServletRequest request);

    void delete(Long id);

    Departamento findByNome(String nome);
}

package br.ind.cmil.gestao.services;

import br.ind.cmil.gestao.domain.Funcionario;
import br.ind.cmil.gestao.model.dto.FuncionarioDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author abraao
 */
public interface FuncionarioService {

    List<FuncionarioDTO> list();

    List<Funcionario> funcionarios();

    Set<Funcionario> funcionarioString(Set<String> funcionarosString);

    FuncionarioDTO buscarFuncionarioPorId(Long id);

    FuncionarioDTO buscarFuncionarioPorNome(String nome);

    Long salvar(FuncionarioDTO funcionario);

    void update(final Long id, FuncionarioDTO funcionarioDTO);

    Map<String, Object> buscarTodos(HttpServletRequest request);

    Map<String, Object> buscarFuncionarioPorCargo(HttpServletRequest request);

    Map<String, Object> buscarHistoricoFuncionario(HttpServletRequest request);

    void delete(Long id);

    long countById();
}

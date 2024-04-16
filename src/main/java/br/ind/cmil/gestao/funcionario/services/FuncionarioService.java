package br.ind.cmil.gestao.funcionario.services;

import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import br.ind.cmil.gestao.funcionario.model.CriarFuncionarioDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ti
 */
public interface FuncionarioService {

    Map<Long, String> funcionarios();

    Map<String, Object> buscarTodos(HttpServletRequest request);

    void delete(final Long id);

    List<CriarFuncionarioDTO> list();

    Set<Funcionario> funcionarioString(Set<String> funcionarosString);

    CriarFuncionarioDTO buscarFuncionarioPorId(Long id);

    CriarFuncionarioDTO buscarFuncionarioPorNome(String nome);

    Long save(CriarFuncionarioDTO funcionario);

    long countById();
}

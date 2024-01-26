package br.ind.cmil.gestao.funcionario.services;
import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import br.ind.cmil.gestao.funcionario.model.FuncionarioDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ti
 */
public interface FuncionarioService {

    List<FuncionarioDTO> getFuncionarios();

    Map<Long, Long> funcionarios();

    Map<String, Object> buscarTodos(HttpServletRequest request);
    

    FuncionarioDTO buscarPorId(final Long id);

    Long create(final FuncionarioDTO funcionarioDTO);

    void update(final Long id, final FuncionarioDTO funcionarioDTO);

    void delete(final Long id);

    String getReferencedWarning(final Long id);
    
    List<FuncionarioDTO> list();

    Set<Funcionario> funcionarioString(Set<String> funcionarosString);

    FuncionarioDTO buscarFuncionarioPorId(Long id);

    FuncionarioDTO buscarFuncionarioPorNome(String nome);

    Long salvar(FuncionarioDTO funcionario);
  

    long countById();
}

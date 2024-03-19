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


    Map<String, Object> buscarTodos(HttpServletRequest request);

    void delete(final Long id);
    
    List<FuncionarioDTO> list();

    Set<Funcionario> funcionarioString(Set<String> funcionarosString);

    FuncionarioDTO buscarFuncionarioPorId(Long id);

    FuncionarioDTO buscarFuncionarioPorNome(String nome);

    Long save(FuncionarioDTO funcionario);
  

    long countById();
}

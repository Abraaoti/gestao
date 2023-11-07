package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.entidades.Funcionario;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author abraao
 */
public interface FuncionarioService {

    List<Funcionario> list(Pageable pageable);

    Funcionario findById(Long id);

    void salvar(Funcionario funcionario);

    Map<String, Object> buscarTodos(HttpServletRequest request);

    void delete(Long id);

    long countById();
}

package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.FuncionarioDTO;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author abraao
 */
public interface IFuncionarioService {

    List<FuncionarioDTO> list(Pageable pageable);

    FuncionarioDTO findById(Long id);

    FuncionarioDTO create(FuncionarioDTO funcionario);

    FuncionarioDTO update(Long id,FuncionarioDTO funcionario);

    void delete(Long id);
}

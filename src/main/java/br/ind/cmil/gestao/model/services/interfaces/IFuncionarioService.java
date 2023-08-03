package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.FuncionarioDTO;
import br.ind.cmil.gestao.model.dto.PessoaDTO;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author abraao
 */
public interface IFuncionarioService {

    List<PessoaDTO> list(Pageable pageable);

    PessoaDTO findById(Long id);

    PessoaDTO create(FuncionarioDTO funcionario);

    void delete(Long id);
}

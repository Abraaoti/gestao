package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.PessoaDTO;
import br.ind.cmil.gestao.model.dto.PessoaJuridicaDTO;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author abraao
 */
public interface IPessoaJuridicaService {

    List<PessoaDTO> list(Pageable pageable);
 
    PessoaDTO findById(Long id);

    PessoaDTO create(PessoaJuridicaDTO funcionario);

    void delete(Long id);
}

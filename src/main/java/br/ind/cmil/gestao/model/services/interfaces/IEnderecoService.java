package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.EnderecoDTO;
import br.ind.cmil.gestao.model.entidades.Endereco;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author abraao
 */
public interface IEnderecoService {

    List<EnderecoDTO> list(Pageable pageable);

    EnderecoDTO buscarEnderecoPorId(Long id);

    EnderecoDTO create(Long pessoa_id, EnderecoDTO e);

    EnderecoDTO update(EnderecoDTO e);

    void delete(Long id);

    Endereco buscarPorCep(String cep);
}

package br.ind.cmil.gestao.services;

import br.ind.cmil.gestao.domain.Endereco;
import br.ind.cmil.gestao.model.dto.EnderecoDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author abraao
 */
public interface EnderecoService {

    List<EnderecoDTO> list(Pageable pageable);

    EnderecoDTO buscarEnderecoPorId(Long id);

    EnderecoDTO criar(Long pessoa_id, EnderecoDTO endereco);

    Long salvar(EnderecoDTO enderecoDTO);

    void delete(Long id);

    Map<String, Object> buscarTodos(HttpServletRequest request);

    EnderecoDTO buscarPorCep(String cep);

    boolean pessoaExists(final Long id);
}

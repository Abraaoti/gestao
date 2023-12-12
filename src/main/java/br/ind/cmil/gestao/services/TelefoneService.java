package br.ind.cmil.gestao.services;

import br.ind.cmil.gestao.domain.Telefone;
import br.ind.cmil.gestao.model.dto.TelefoneDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author abraao
 */
public interface TelefoneService {

    List<TelefoneDTO> list(Pageable pageable);

    TelefoneDTO criar(Long pessoa_id, TelefoneDTO telefone);

    Long salvar(TelefoneDTO telefone);

    TelefoneDTO buscarTelefonePorId(Long id);

    void delete(Long id);

    Map<String, Object> buscarTodos(HttpServletRequest request);

    TelefoneDTO buscarPorNumero(String numero);

}

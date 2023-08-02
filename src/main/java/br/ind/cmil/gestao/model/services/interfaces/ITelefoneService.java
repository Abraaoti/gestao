package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.TelefoneDTO;
import br.ind.cmil.gestao.model.entidades.Telefone;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author abraao
 */
public interface ITelefoneService {

    List<TelefoneDTO> list(Pageable pageable);

    TelefoneDTO save(Long pessoa_id,TelefoneDTO telefone);

    TelefoneDTO buscarTelefonePorId(Long id);

    void delete(Long id);

    TelefoneDTO buscarPorNumero(String numero);

}

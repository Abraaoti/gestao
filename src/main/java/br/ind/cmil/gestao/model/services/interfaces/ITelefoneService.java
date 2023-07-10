package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.entidades.Telefone;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
public interface ITelefoneService {

    Telefone save(Telefone telefone);

    Telefone buscarTelefonePorId(Long id);

    void delete(Long id);

    Telefone update(Telefone telefoneDTO);

    Telefone buscarPorNumero(String numero);
    
      @Transactional
    void deleteById(Long id);

    @Transactional
    void deleteByTutorialId(Long pessoaId);

}

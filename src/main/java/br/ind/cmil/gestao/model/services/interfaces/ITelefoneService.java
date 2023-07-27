package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.entidades.Telefone;

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

  

}

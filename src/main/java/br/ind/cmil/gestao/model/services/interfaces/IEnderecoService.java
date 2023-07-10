package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.entidades.Endereco;

/**
 *
 * @author abraao
 */
public interface IEnderecoService {

    void save(Endereco endereco);

    Endereco buscarEnderecoPorId(Long id);

    void delete(Long id);

    void update(Endereco endereco);

    Endereco buscarPorCep(String cep);
}

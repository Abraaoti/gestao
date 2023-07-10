package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.entidades.Pessoa;
import java.util.List;

/**
 *
 * @author abraao
 */
public interface IPessoaService {

   List<Pessoa> getAllNome( String nome);

    Pessoa getPessoaById(Long id);

    Pessoa createPessoa(Pessoa pessoa);

    Pessoa updatePessoa(Long id, Pessoa pessoa);

    void delete(Long id);

    void deleteAllPessoa();

    List<Pessoa> findByPublished();

}

package br.ind.cmil.gestao.pessoa.service;

import br.ind.cmil.gestao.pessoa.dto.PessoaDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *
 * @author abraaocalelessocassi
 */
public interface PessoaService {

    Long salve(PessoaDTO pessoa);

    PessoaDTO buscarPorId(final Long id);

    void update(final Long id, final PessoaDTO pessoaDTO);

    void delete(final Long id);

    List<PessoaDTO> getPessoas();

    Map<String, Object> buscarTodos(HttpServletRequest request);

    Long create(final PessoaDTO pessoaDTO);

}

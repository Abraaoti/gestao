package br.ind.cmil.gestao.pessoa.service;

import br.ind.cmil.gestao.pessoa.domain.PessoaJuridica;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *
 * @author abraaocalelessocassi
 */
public interface PessoaJuridicaService {

    PessoaJuridica save(PessoaJuridica empresa);

    PessoaJuridica update(PessoaJuridica empresa);

    PessoaJuridica buscarEmpresaPorId(Long id);

    void delete(final Long id);

    List<PessoaJuridica> getEmpresas();

    Map<String, Object> buscarTodos(HttpServletRequest request);

}

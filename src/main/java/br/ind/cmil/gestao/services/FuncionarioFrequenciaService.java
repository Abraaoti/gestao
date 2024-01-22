package br.ind.cmil.gestao.services;

import br.ind.cmil.gestao.domain.FuncionarioFrequencia;
import br.ind.cmil.gestao.model.dto.FuncionarioFrequenciaDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *
 * @author abraao
 */
public interface FuncionarioFrequenciaService {

    FuncionarioFrequenciaDTO form(Long funcionarioId, Long frequenciaId, FuncionarioFrequenciaDTO frequenciaDTO);

    Long salvar(FuncionarioFrequenciaDTO frequenciaDTO);

    FuncionarioFrequenciaDTO buscarPorId(Long id);

    List<FuncionarioFrequencia> getFuncionarioFrequencias();

    Map<String, Object> buscarFuncionarioFrequencias(HttpServletRequest request);

    void delete(Long id);

}

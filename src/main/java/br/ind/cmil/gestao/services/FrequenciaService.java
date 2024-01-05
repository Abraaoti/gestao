package br.ind.cmil.gestao.services;

import br.ind.cmil.gestao.domain.Frequencia;
import br.ind.cmil.gestao.model.dto.FrequenciaDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *
 * @author abraao
 */
public interface FrequenciaService {

    FrequenciaDTO criar(List<Long> funcionario_ids, FrequenciaDTO frequencia);

    Long salvar(FrequenciaDTO frequenciaDTO);

    FrequenciaDTO findById(Long id);

    List<Frequencia> getFrequencias();

    List<Frequencia> f();

    Map<String, Object> frequencias(HttpServletRequest request);

    Map<String, Object> funcionariosFrequencias(HttpServletRequest request);

    void delete(Long id);

}

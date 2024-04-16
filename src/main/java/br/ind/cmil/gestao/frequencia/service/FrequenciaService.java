package br.ind.cmil.gestao.frequencia.service;

import br.ind.cmil.gestao.frequencia.model.FrequenciaDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ti
 */
public interface FrequenciaService {

    void salvar(FrequenciaDTO frequenciaDTO);

    void update(final Long id, final FrequenciaDTO frequenciaDTO);

    FrequenciaDTO buscarPorId(Long id);

    FrequenciaDTO buscarFrequenciaPorTipo(String tipo);

    List<FrequenciaDTO> getFrequencias();

    Map<String, Object> buscarFrequencias(HttpServletRequest request);

    void delete(Long id);

}

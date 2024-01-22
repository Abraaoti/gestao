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

    Long salvar(FrequenciaDTO frequenciaDTO);

    FrequenciaDTO buscarPorId(Long id);

    FrequenciaDTO buscarFrequenciaPorTipo(String tipo);

    List<Frequencia> getFrequencias();

    Map<String, Object> buscarFrequencias(HttpServletRequest request);

    void delete(Long id);

}

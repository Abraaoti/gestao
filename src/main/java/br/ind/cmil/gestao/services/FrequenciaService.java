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

    FrequenciaDTO findById(Long id);

    List<Frequencia> getFrequencias();

    Map<String, Object> funcionariosFrequencias(HttpServletRequest request);

    void delete(Long id);

}

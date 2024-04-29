package br.ind.cmil.gestao.frequencia.service;

import br.ind.cmil.gestao.frequencia.model.FrequenciaDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ti
 */
public interface FrequenciaService {

    void salvar(FrequenciaDTO frequenciaDTO);

    void baterPonto(Long funcionarioId, LocalTime horaAtual);

    FrequenciaDTO buscarPorId(Long id);

    List<FrequenciaDTO> getFrequencias();

    Map<String, Object> buscarFrequencias(HttpServletRequest request);

    void delete(Long id);

}

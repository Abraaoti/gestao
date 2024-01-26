
package br.ind.cmil.gestao.presenca.service;

import br.ind.cmil.gestao.model.dto.PresencaDTO;
import br.ind.cmil.gestao.presenca.domain.Presenca;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ti
 */
public interface PresencaService {
     Presenca salvar(Presenca presenca);

    Presenca update(@Positive @NotNull Long id, @Valid Presenca presenca);

    PresencaDTO findById(Long id);

    List<Presenca> getPresencas();

    Map<String, Object> frequencias(HttpServletRequest request);

    void delete(Long id);
}

package br.ind.cmil.gestao.services;

import br.ind.cmil.gestao.domain.Presenca;
import br.ind.cmil.gestao.model.dto.PresencaDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import java.util.Map;

/**
 *
 * @author abraao
 */
public interface PresencaService {

    Presenca salvar(Presenca presenca);

    Presenca update(@Positive @NotNull Long id, @Valid Presenca presenca);

    PresencaDTO findById(Long id);

    List<Presenca> getPresencas();

    Map<String, Object> frequencias(HttpServletRequest request);

    void delete(Long id);

}

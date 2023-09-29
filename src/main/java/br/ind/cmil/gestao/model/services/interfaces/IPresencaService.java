package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.PresencaDTO;
import br.ind.cmil.gestao.model.entidades.Horario;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author abraao
 */
public interface IPresencaService {

    PresencaDTO findById(Long id);

    void create(PresencaDTO p);

    List<Horario> buscarHorarios(Long id, LocalDate data);

    Map<String, Object> presencas(HttpServletRequest request);

    void delete(Long id);
}

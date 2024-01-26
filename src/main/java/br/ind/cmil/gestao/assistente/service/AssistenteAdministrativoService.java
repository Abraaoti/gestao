package br.ind.cmil.gestao.assistente.service;

import br.ind.cmil.gestao.assistente.domain.AssistenteAdministrativo;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ti
 */
public interface AssistenteAdministrativoService {

    @Transactional(readOnly = false)
    void create(AssistenteAdministrativo a);

    @Transactional(readOnly = true)
    Map<String, Object> assistentes(HttpServletRequest request);

    @Transactional(readOnly = false)
    void delete(Long id);

    @Transactional(readOnly = true)
    AssistenteAdministrativo buscarPorUsuarioId(Long id);

    @Transactional(readOnly = true)
    AssistenteAdministrativo buscarPorEmail(String email);
}

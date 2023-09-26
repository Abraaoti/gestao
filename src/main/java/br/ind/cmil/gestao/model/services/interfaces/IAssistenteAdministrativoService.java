package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.AdministradorDTO;
import br.ind.cmil.gestao.model.dto.AssistenteAdministrativoDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
public interface IAssistenteAdministrativoService {

    @Transactional(readOnly = false)
    void create(AssistenteAdministrativoDTO a);

    @Transactional(readOnly = true)
    AssistenteAdministrativoDTO findById(Long id);

    @Transactional(readOnly = true)
    AssistenteAdministrativoDTO form(AssistenteAdministrativoDTO assistente, @AuthenticationPrincipal User user);

    @Transactional(readOnly = true)
    Map<String, Object> assistentes(HttpServletRequest request);

    @Transactional(readOnly = false)
    void delete(Long id);

    @Transactional(readOnly = true)
    AssistenteAdministrativoDTO buscarPorUsuarioId(Long id);

    @Transactional(readOnly = true)
    AssistenteAdministrativoDTO buscarPorEmail(String email);

}

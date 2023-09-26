package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.AdministradorDTO;
import br.ind.cmil.gestao.model.dto.AuxiliarAdministrativoDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
public interface IAuxiliarAdministrativoService {

    @Transactional(readOnly = false)
    void create(AuxiliarAdministrativoDTO auxiliar);

    @Transactional(readOnly = true)
    AuxiliarAdministrativoDTO findById(Long id);

    @Transactional(readOnly = true)
    AuxiliarAdministrativoDTO form(AuxiliarAdministrativoDTO auxiliar, @AuthenticationPrincipal User user);

    @Transactional(readOnly = true)
    Map<String, Object> aux(HttpServletRequest request);

    @Transactional(readOnly = false)
    void delete(Long id);

    @Transactional(readOnly = true)
    AuxiliarAdministrativoDTO buscarPorUsuarioId(Long id);

    @Transactional(readOnly = true)
    AuxiliarAdministrativoDTO buscarPorEmail(String email);

}

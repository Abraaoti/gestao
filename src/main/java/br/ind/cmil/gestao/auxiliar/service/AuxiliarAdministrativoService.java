
package br.ind.cmil.gestao.auxiliar.service;

import br.ind.cmil.gestao.auxiliar.domain.AuxiliarAdministrativo;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ti
 */
public interface AuxiliarAdministrativoService {
    
    @Transactional(readOnly = false)
    void create(AuxiliarAdministrativo auxiliar);

  

    @Transactional(readOnly = true)
    AuxiliarAdministrativo form(AuxiliarAdministrativo auxiliar, @AuthenticationPrincipal User user);

    @Transactional(readOnly = true)
    Map<String, Object> aux(HttpServletRequest request);

    @Transactional(readOnly = false)
    void delete(Long id);

    @Transactional(readOnly = true)
    AuxiliarAdministrativo buscarPorUsuarioId(Long id);

    @Transactional(readOnly = true)
    AuxiliarAdministrativo buscarPorEmail(String email);

    Set<AuxiliarAdministrativo> list(Pageable pageable);

}

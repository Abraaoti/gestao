package br.ind.cmil.gestao.assistente.service;

import br.ind.cmil.gestao.assistente.domain.AssistenteAdministrativo;
import br.ind.cmil.gestao.assistente.model.AssistenteAdministrativoDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ti
 */
public interface AssistenteAdministrativoService {

  
    void create(AssistenteAdministrativoDTO a);

    @Transactional(readOnly = true)
    Map<String, Object> assistentes(HttpServletRequest request);

    @Transactional(readOnly = false)
    void delete(Long id);

    @Transactional(readOnly = true)
    AssistenteAdministrativo buscarPorUsuarioId(Long id);

    
    AssistenteAdministrativoDTO buscarPorEmail(AssistenteAdministrativoDTO assistenteAdministrativoDTO,@AuthenticationPrincipal User email);
}

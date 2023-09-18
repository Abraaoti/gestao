package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.AdministradorDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
public interface IAdministradorService {

    @Transactional(readOnly = false)
    void create(AdministradorDTO a);

    @Transactional(readOnly = true)
    AdministradorDTO findById(Long id);

    @Transactional(readOnly = true)
    AdministradorDTO form(AdministradorDTO administrador, @AuthenticationPrincipal User user);

    @Transactional(readOnly = true)
    Map<String, Object> administradores(HttpServletRequest request);

    @Transactional(readOnly = false)
    void delete(Long id);

    @Transactional(readOnly = true)
    AdministradorDTO buscarPorUsuarioId(Long id);

    @Transactional(readOnly = true)
    AdministradorDTO buscarPorEmail(String email);

}

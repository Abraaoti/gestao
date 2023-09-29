package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.DiretorDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
public interface IDiretorService {

    @Transactional(readOnly = false)
    void create(DiretorDTO a);

    @Transactional(readOnly = true)
    DiretorDTO findById(Long id);

    @Transactional(readOnly = true)
    DiretorDTO form(DiretorDTO diretor, @AuthenticationPrincipal User user);

    @Transactional(readOnly = true)
    Map<String, Object> diretores(HttpServletRequest request);

    @Transactional(readOnly = false)
    void delete(Long id);

    @Transactional(readOnly = true)
    DiretorDTO buscarPorUsuarioId(Long id);

    @Transactional(readOnly = true)
    DiretorDTO buscarPorEmail(String email);

}

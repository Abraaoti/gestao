package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.entidades.Administrador;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 * @author abraao
 */
public interface IAdministradorService {

    void salvar(Administrador a);

    Administrador buscarPorUsuarioId(Long id);

    Administrador buscarPorEmail(String email);

    Map<String, Object> administradores(HttpServletRequest request);

    void delete(Long id);

}

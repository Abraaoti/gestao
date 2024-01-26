package br.ind.cmil.gestao.administrador.services;

import br.ind.cmil.gestao.administrador.domain.Administrador;
import br.ind.cmil.gestao.administrador.model.AdministradorDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 * @author ti
 */
public interface AdministradorService {

    Administrador checarDados(String nome);

    void salvar(AdministradorDTO a);

    AdministradorDTO buscarPorUsuarioId(Long id);

    AdministradorDTO buscarPorEmail(String email);

    Map<String, Object> administradores(HttpServletRequest request);

    void delete(Long id);
}

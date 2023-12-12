package br.ind.cmil.gestao.services;

import br.ind.cmil.gestao.model.dto.AdministradorDTO;
import br.ind.cmil.gestao.domain.Administrador;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 * @author abraao
 */
public interface AdministradorService {

    Administrador checarDados(String nome);

    void salvar(AdministradorDTO a);

    AdministradorDTO buscarPorUsuarioId(Long id);

    AdministradorDTO buscarPorEmail(String email);

    Map<String, Object> administradores(HttpServletRequest request);

    void delete(Long id);

}

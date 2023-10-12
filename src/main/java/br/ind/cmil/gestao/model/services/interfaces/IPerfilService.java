package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.PerfilDTO;
import br.ind.cmil.gestao.model.entidades.Perfil;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author abraao
 */
public interface IPerfilService {

    Set<PerfilDTO> list(Pageable pageable);

    Set<PerfilDTO> perfis();

    List<Perfil> perfis(List<String> perfil);

    PerfilDTO findById(Long id);

    PerfilDTO buscarPerfilPorNome(String name);

    PerfilDTO create(PerfilDTO p);

   

    String tipoPerfil(String tipo);

    PerfilDTO update(PerfilDTO p);

    Map<String, Object> buscarTodos(HttpServletRequest request);

    boolean checkIfPerfilExist(String name);

    void delete(Long id);
    Boolean atualizarPerfisEUsuarios(List<String> roles);

    // Perfil getOrCreate(String name);
}

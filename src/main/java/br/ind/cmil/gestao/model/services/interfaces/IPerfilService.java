package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.PerfilDTO;
import br.ind.cmil.gestao.model.entidades.Perfil;
import br.ind.cmil.gestao.model.enums.TipoPerfil;
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

    Set<Perfil> perfis(Set<String> perfil);

    PerfilDTO findById(Long id);

    PerfilDTO buscarPerfilPorNome(String name);

    PerfilDTO create(PerfilDTO p);

   

    String tipoPerfil(String tipo);

    PerfilDTO update(PerfilDTO p);

    Map<String, Object> buscarTodos(HttpServletRequest request);

    boolean checkIfPerfilExist(String name);

    void delete(Long id);

    // Perfil getOrCreate(String name);
}

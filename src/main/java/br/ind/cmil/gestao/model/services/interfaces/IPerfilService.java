package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.PerfilDTO;
import br.ind.cmil.gestao.model.entidades.Perfil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import java.util.Set;

/**
 *
 * @author abraao
 */
public interface IPerfilService {

    List<PerfilDTO> list();

    Set<Perfil> perfis(Set<String> perfil);

    PerfilDTO findById(Long id);

    PerfilDTO buscarPerfilPorNome(String name);

    PerfilDTO create(PerfilDTO p);

    PerfilDTO update(PerfilDTO p);

    boolean checkIfPerfilExist(String name);

    void delete(Long id);
}

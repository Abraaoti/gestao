package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.PerfilDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

/**
 *
 * @author abraao
 */
public interface IPerfilService {

    List<PerfilDTO> list();

    PerfilDTO findById(@NotNull @Positive Long id);

    PerfilDTO create(@Valid @NotNull PerfilDTO p);

    PerfilDTO update(@NotNull @Positive Long id, @Valid @NotNull PerfilDTO p);

    void delete(@NotNull @Positive Long id);
}

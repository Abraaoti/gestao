package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.FuncionarioDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

/**
 *
 * @author abraao
 */
public interface IFuncionarioservice {

    List<FuncionarioDTO> list();

    FuncionarioDTO findById(@NotNull @Positive Long id);

    FuncionarioDTO create(@Valid @NotNull FuncionarioDTO funcionario);

    FuncionarioDTO update(@NotNull @Positive Long id, @Valid @NotNull FuncionarioDTO funcionario);

    void delete(@NotNull @Positive Long id);
}

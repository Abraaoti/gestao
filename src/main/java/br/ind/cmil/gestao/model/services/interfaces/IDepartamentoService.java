package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.DepartamentoDTO;
import br.ind.cmil.gestao.model.entidades.Departamento;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author abraao
 */
public interface IDepartamentoService {

    List<DepartamentoDTO> list(Pageable pageable);

    DepartamentoDTO findById(Long id);

    DepartamentoDTO create(DepartamentoDTO dep);

    DepartamentoDTO update(Long id, DepartamentoDTO dep);

    void delete(Long id);

     Departamento findByNome(String nome);

}
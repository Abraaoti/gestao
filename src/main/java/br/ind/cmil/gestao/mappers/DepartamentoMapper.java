package br.ind.cmil.gestao.mappers;

import br.ind.cmil.gestao.model.dto.DepartamentoDTO;
import br.ind.cmil.gestao.domain.Departamento;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class DepartamentoMapper {

    public DepartamentoDTO toDTO(Departamento d) {
        if (d== null) {
            return null;
        }
        return new DepartamentoDTO(d.getId(), d.getNome());
    }

    public Departamento toEntity(DepartamentoDTO dto) {
        if (dto == null) {
            return null;
        }
        Departamento d = new Departamento();
        d.setNome(dto.nome());
        d.setId(dto.id());
        return d;
    }

}


package br.ind.cmil.gestao.departamento.mapper;

import br.ind.cmil.gestao.departamento.domain.Departamento;
import br.ind.cmil.gestao.departamento.model.DepartamentoDTO;
import org.springframework.stereotype.Component;

/**
 *
 * @author ti
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

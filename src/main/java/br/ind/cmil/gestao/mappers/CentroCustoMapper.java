package br.ind.cmil.gestao.mappers;

import br.ind.cmil.gestao.model.dto.CentroCustoDTO;
import br.ind.cmil.gestao.domain.CentroCusto;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class CentroCustoMapper {

    public CentroCustoDTO toDTO(CentroCusto centroCusto) {

        return new CentroCustoDTO(centroCusto.getId(), centroCusto.getNome());

    }

    public CentroCusto toEntity(CentroCustoDTO dto) {
        CentroCusto centro = new CentroCusto();
        centro.setNome(dto.nome());

        return centro;
    }

}

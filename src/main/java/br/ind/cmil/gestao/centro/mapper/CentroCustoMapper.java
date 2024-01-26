package br.ind.cmil.gestao.centro.mapper;

import br.ind.cmil.gestao.centro.domain.CentroCusto;
import br.ind.cmil.gestao.centro.model.CentroCustoDTO;
import org.springframework.stereotype.Component;

/**
 *
 * @author ti
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

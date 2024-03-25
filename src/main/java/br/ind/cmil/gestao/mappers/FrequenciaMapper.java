package br.ind.cmil.gestao.mappers;

import br.ind.cmil.gestao.enums.TipoFrequencia;
import br.ind.cmil.gestao.frequencia.domain.Frequencia;
import br.ind.cmil.gestao.model.dto.FrequenciaDTO;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class FrequenciaMapper {

    public FrequenciaDTO toDTO(Frequencia frequencia) {
        return new FrequenciaDTO(frequencia.getId(), frequencia.getStatus().getValue().toLowerCase());
    }

    public Frequencia toEntity(FrequenciaDTO dto) {
        if (dto == null) {
            return null;
        }
        Frequencia frequencia = new Frequencia();
        frequencia.setId(dto.id());
        frequencia.setStatus(TipoFrequencia.convertTipoTipoFrequencia(dto.status()));
        return frequencia;
    }

}

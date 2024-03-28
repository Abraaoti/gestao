
package br.ind.cmil.gestao.frequencia.mappers;

import br.ind.cmil.gestao.enums.TipoFrequencia;
import br.ind.cmil.gestao.frequencia.domain.Frequencia;
import br.ind.cmil.gestao.frequencia.model.FrequenciaDTO;
import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraaocalelessocassi
 */
@Component
public class FrequenciaMapper {
     public FrequenciaDTO toDTO(Frequencia frequencia) {
        return new FrequenciaDTO(frequencia.getId(), frequencia.getStatus().getValue().toLowerCase(), frequencia.getFuncionario().getNome());
    }

    public Frequencia toEntity(FrequenciaDTO dto) {
        if (dto == null) {
            return null;
        }
        Frequencia frequencia = new Frequencia();
        frequencia.setId(dto.id());
        frequencia.setStatus(TipoFrequencia.convertTipoTipoFrequencia(dto.status()));
        frequencia.setFuncionario(new Funcionario(dto.funcionario()));
        return frequencia;
    }
}

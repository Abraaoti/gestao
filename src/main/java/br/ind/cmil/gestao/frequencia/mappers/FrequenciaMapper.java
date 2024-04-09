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
        Long funcionario = frequencia.getFuncionario().getId();
        return new FrequenciaDTO(frequencia.getId(), frequencia.getStatus().getValue().toLowerCase(), frequencia.getData(), frequencia.getEntradaManha(), frequencia.getSaidaManha(), frequencia.getEntradaTarde(), frequencia.getSaidaTarde(), funcionario);
    }

    public Frequencia toEntity(FrequenciaDTO dto) {
        if (dto == null) {
            return null;
        }
        Frequencia frequencia = new Frequencia();
        frequencia.setId(dto.id());
        frequencia.setStatus(TipoFrequencia.convertTipoTipoFrequencia(dto.status()));
        frequencia.setData(dto.data());
        frequencia.setEntradaManha(dto.entradaManha());
        frequencia.setSaidaManha(dto.saidaManha());
        frequencia.setEntradaTarde(dto.entradaTarde());
        frequencia.setSaidaTarde(dto.saidaTarde());
        frequencia.setFuncionario(new Funcionario(dto.funcionario()));
        return frequencia;
    }
}

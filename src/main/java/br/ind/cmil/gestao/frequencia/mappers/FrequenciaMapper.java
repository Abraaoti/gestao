
package br.ind.cmil.gestao.frequencia.mappers;

import br.ind.cmil.gestao.enums.TipoFrequencia;
import br.ind.cmil.gestao.frequencia.domain.Frequencia;
import br.ind.cmil.gestao.frequencia.model.FrequenciaDTO;
import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraaocalelessocassi
 */
@Component
public class FrequenciaMapper {
     public FrequenciaDTO toDTO(Frequencia frequencia) {
         List<Long> funcionarios = frequencia.getFuncionarios().stream().map(funcionario-> funcionario.getId()).collect(Collectors.toList());
        return new FrequenciaDTO(frequencia.getId(), frequencia.getStatus().getValue().toLowerCase(),frequencia.getData(),frequencia.getEntradaManha(),frequencia.getSaidaManha(), frequencia.getEntradaTarde(), frequencia.getSaidaTarde(), frequencia.getEntradaExtra(),frequencia.getSaidaExtra(), funcionarios);
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
        frequencia.setEntradaExtra(dto.saidaExtra());
        Set<Funcionario> funcionarios = dto.funcionarios().stream().map(funcionario -> new Funcionario(funcionario)).collect(Collectors.toSet());
        frequencia.setFuncionarios(funcionarios);
        return frequencia;
    }
}

package br.ind.cmil.gestao.mappers;

import br.ind.cmil.gestao.domain.Frequencia;
import br.ind.cmil.gestao.domain.Funcionario;
import br.ind.cmil.gestao.enums.TipoFrequencia;
import br.ind.cmil.gestao.model.dto.FrequenciaDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class FrequenciaMapper {

    public FrequenciaDTO toDTO(Frequencia frequencia) {
        List<Long> funcionarios = frequencia.getFuncionarios().stream().map(funcionario -> funcionario.getId()).collect(Collectors.toList());
        return new FrequenciaDTO(frequencia.getId(), frequencia.getData(), frequencia.getStatus().getValue(), funcionarios);
    }

    public Frequencia toEntity(FrequenciaDTO dto) {
        if (dto == null) {
            return null;
        }
        Frequencia frequencia = new Frequencia();
        frequencia.setId(dto.id());
        LocalDate data = (dto.data() == null) ? LocalDate.now() : dto.data();
        frequencia.setData(data);
        frequencia.setStatus(TipoFrequencia.convertTipoTipoFrequencia(dto.status()));
        List<Funcionario> funcionarios = dto.funcionario().stream().map(funcionaro -> new Funcionario(funcionaro)).toList();
        frequencia.setFuncionarios(funcionarios);
        return frequencia;
    }

}

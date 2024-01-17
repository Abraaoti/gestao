package br.ind.cmil.gestao.mappers;

import br.ind.cmil.gestao.domain.Frequencia;
import br.ind.cmil.gestao.domain.Funcionario;
import br.ind.cmil.gestao.enums.TipoFrequencia;
import br.ind.cmil.gestao.model.dto.FrequenciaDTO;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class FrequenciaMapper {

    public FrequenciaDTO toDTO(Frequencia frequencia) {
        Set<String> funcionarios = frequencia.getFuncionarios().stream().map(Funcionario::getNome).collect(Collectors.toSet());
        LocalDate data =  frequencia.getData()==null? LocalDate.now(): frequencia.getData();
        return new FrequenciaDTO(frequencia.getId(), data, frequencia.getStatus().getValue().toLowerCase(), funcionarios);
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
        Set<Funcionario> funcionarios = dto.funcionarios().stream().map(Funcionario::new).collect(Collectors.toSet());
        frequencia.setFuncionarios(funcionarios);
        return frequencia;
    }

}

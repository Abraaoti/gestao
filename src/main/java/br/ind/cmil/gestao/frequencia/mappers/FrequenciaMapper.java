package br.ind.cmil.gestao.frequencia.mappers;

import br.ind.cmil.gestao.frequencia.domain.Frequencia;
import br.ind.cmil.gestao.frequencia.model.FrequenciaDTO;
import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraaocalelessocassi
 */
@Component
public class FrequenciaMapper {

    public FrequenciaDTO toDTO(Frequencia frequencia) {
        Long funcionario = frequencia.getFuncionario().getId();
        LocalDate dataAtual = (frequencia.getData() == null) ? LocalDate.now() : frequencia.getData();
        return new FrequenciaDTO(frequencia.getId(), dataAtual, funcionario);
    }

    public Frequencia toEntity(FrequenciaDTO dto) {

        Frequencia frequencia = new Frequencia();
        frequencia.setId(dto.id());
        frequencia.setEntrada(null);
        frequencia.setIntervalo(null);
        frequencia.setRetorno(null);
        frequencia.setSaida(null);
        frequencia.setHoraAtual(null);
        frequencia.setData(dto.dataAtual());
        Funcionario funcionario = new Funcionario();
        funcionario.setId(dto.funcionario());
        frequencia.setFuncionario(funcionario);
        return frequencia;
    }
}

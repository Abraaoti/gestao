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
        return new FrequenciaDTO(frequencia.getId(), frequencia.getHoraAtual(), funcionario);
    }

    public Frequencia toEntity(FrequenciaDTO dto) {

        Frequencia frequencia = new Frequencia();      
        frequencia.setId(dto.id());
        frequencia.setEntrada(dto.horaAtual());
        frequencia.setIntervalo(null);
        frequencia.setRetorno(null);
        frequencia.setSaida(null);
        frequencia.setHoraAtual(dto.horaAtual());
        frequencia.setData(LocalDate.now());
        Funcionario funcionario = new Funcionario();
        funcionario.setId(dto.funcionario());
        frequencia.setFuncionario(funcionario);
        return frequencia;
    }
}

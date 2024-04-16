package br.ind.cmil.gestao.frequencia.mappers;

import br.ind.cmil.gestao.enums.TipoFrequencia;
import br.ind.cmil.gestao.frequencia.domain.Frequencia;
import br.ind.cmil.gestao.frequencia.model.FrequenciaDTO;
import br.ind.cmil.gestao.funcionario.repository.FuncionarioRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraaocalelessocassi
 */
@Component
public class FrequenciaMapper {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public FrequenciaDTO toDTO(Frequencia frequencia) {
        Long funcionario = frequencia.getFuncionario().getId();
        return new FrequenciaDTO(frequencia.getId(), frequencia.getHoraAtual(), funcionario);
    }

    public Frequencia toEntity(FrequenciaDTO dto) {
        if (dto == null) {
            return null;
        }
        Frequencia frequencia = new Frequencia();
        frequencia.setId(dto.id());
        frequencia.setStatus(TipoFrequencia.convertTipoTipoFrequencia("presente"));
        frequencia.setData(LocalDate.now());
        frequencia.setFuncionario(funcionarioRepository.findById(dto.funcionario()).get());
        return frequencia;
    }
}

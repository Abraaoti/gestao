package br.ind.cmil.gestao.mappers;

import br.ind.cmil.gestao.domain.Frequencia;
import br.ind.cmil.gestao.domain.Funcionario;
import br.ind.cmil.gestao.domain.FuncionarioFrequencia;
import br.ind.cmil.gestao.enums.TipoFrequencia;
import br.ind.cmil.gestao.model.dto.FuncionarioFrequenciaDTO;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class FuncionarioFrequenciaMapper {

    public FuncionarioFrequenciaDTO toDTO(FuncionarioFrequencia fufrequencia) {
        String funcionario =  (fufrequencia.getFuncionario().getId() == null) ? null : fufrequencia.getFuncionario().getNome();
        String frequencia = (fufrequencia.getFrequencia().getId() == null) ? null : fufrequencia.getFrequencia().getStatus().getValue().toLowerCase();
        return new FuncionarioFrequenciaDTO(fufrequencia.getId(), funcionario,frequencia);
    }

    public FuncionarioFrequencia toEntity(FuncionarioFrequenciaDTO dto) {
        if (dto == null) {
            return null;
        }
        FuncionarioFrequencia frequencia = new FuncionarioFrequencia();
        frequencia.setId(dto.id());
        frequencia.setData(LocalDate.now());
        frequencia.setFuncionario(new Funcionario(dto.funcionario()));
        frequencia.setFrequencia(new Frequencia(TipoFrequencia.convertTipoTipoFrequencia(dto.frequencia())));
        return frequencia;
    }

}

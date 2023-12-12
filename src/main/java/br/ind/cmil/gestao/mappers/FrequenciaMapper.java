package br.ind.cmil.gestao.mappers;

import br.ind.cmil.gestao.domain.Frequencia;
import br.ind.cmil.gestao.domain.Funcionario;
import br.ind.cmil.gestao.model.dto.FrequenciaDTO;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class FrequenciaMapper {
    
    public FrequenciaDTO toDTO(Frequencia frequencia) {       
       Long funcionario = (frequencia.getFuncionario().getId() ==null )? null:frequencia.getFuncionario().getId();
        return new FrequenciaDTO(frequencia.getId(), frequencia.getData(), frequencia.getStatus(), funcionario);
    }
    
    public Frequencia toEntity(FrequenciaDTO dto) {
        if (dto == null) {
            return null;
        } 
        Frequencia frequencia = new Frequencia();
        frequencia.setId(dto.id());
        frequencia.setData(dto.data());
        frequencia.setStatus(dto.status());
        frequencia.setFuncionario(new Funcionario(dto.id()));
        return frequencia;
    }
    
   
    
}

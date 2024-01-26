
package br.ind.cmil.gestao.mappers;

/**
 *
 * @author abraao
 */


import br.ind.cmil.gestao.centro.model.CentroCustoDTO;
import br.ind.cmil.gestao.domain.Lotacao;
import org.springframework.stereotype.Component;
@Component
public class LotacaoMapper {

    public CentroCustoDTO toDTO(Lotacao l) {
        
        return new CentroCustoDTO(l.getId(), l.getNome());
       
    }

    public Lotacao toEntity( Lotacao centro,CentroCustoDTO dto) {
      
        centro.setNome(dto.nome());        
       
        return centro;
    }
    
}

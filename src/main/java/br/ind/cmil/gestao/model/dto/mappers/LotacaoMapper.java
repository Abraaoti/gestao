
package br.ind.cmil.gestao.model.dto.mappers;

/**
 *
 * @author abraao
 */


import br.ind.cmil.gestao.dto.LotacaoDTO;
import br.ind.cmil.gestao.model.entidades.Lotacao;
import org.springframework.stereotype.Component;
@Component
public class LotacaoMapper {

    public LotacaoDTO toDTO(Lotacao l) {
        if (l == null) {
            return null;
        }
      

        return new LotacaoDTO(l.getId(), l.getNome());
       
    }

    public Lotacao toEntity(LotacaoDTO dto) {
        if (dto == null) {
            return null;
        }
        Lotacao l = new Lotacao();
        l.setId(dto.id());
        l.setNome(dto.nome());        
        AdministradorMapper dm = new AdministradorMapper();
       
        return l;
    }
    
}

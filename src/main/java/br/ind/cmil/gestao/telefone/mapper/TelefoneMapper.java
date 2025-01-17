
package br.ind.cmil.gestao.telefone.mapper;

import br.ind.cmil.gestao.enums.TipoTelefone;
import br.ind.cmil.gestao.pessoa.domain.Pessoa;
import br.ind.cmil.gestao.telefone.domain.Telefone;
import br.ind.cmil.gestao.telefone.model.TelefoneDTO;
import org.springframework.stereotype.Component;

/**
 *
 * @author ti
 */
@Component
public class TelefoneMapper {
    
    public TelefoneDTO toDTO(Telefone telefone) {       
       String pessoa = (telefone.getPessoa().getNome()==null )? null:telefone.getPessoa().getNome();
        return new TelefoneDTO(telefone.getId(), telefone.getNumero(), telefone.getTipo().getValue(), pessoa);
    }
    
    public Telefone toEntity(TelefoneDTO dto) {
        if (dto == null) {
            return null;
        } 
        Telefone telefone = new Telefone();
        telefone.setId(dto.id());
        telefone.setNumero(dto.numero());
        telefone.setTipo(TipoTelefone.convertTelefoneValue(dto.tipo()));
        telefone.setPessoa(new Pessoa(dto.pessoa()));
        return telefone;
    }
    
   
    
}

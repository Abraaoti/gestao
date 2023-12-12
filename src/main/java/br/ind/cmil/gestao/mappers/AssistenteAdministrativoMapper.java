package br.ind.cmil.gestao.mappers;

import br.ind.cmil.gestao.model.dto.AssistenteAdministrativoDTO;
import br.ind.cmil.gestao.domain.AssistenteAdministrativo;
import br.ind.cmil.gestao.domain.Usuario;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class AssistenteAdministrativoMapper {
    
    public AssistenteAdministrativoDTO toDTO(AssistenteAdministrativo assistente) {
        Long usuario = (assistente.getUsuario().getId() == null) ? null : assistente.getUsuario().getId();
        return new AssistenteAdministrativoDTO(assistente.getId(), assistente.getNome(), usuario);
    }
    
    public AssistenteAdministrativo toEntity(AssistenteAdministrativoDTO dto) {
        AssistenteAdministrativo assistente = new AssistenteAdministrativo();
        assistente.setNome(dto.nome());
        assistente.setUsuario(new Usuario(dto.id()));
        return assistente;
    }
}

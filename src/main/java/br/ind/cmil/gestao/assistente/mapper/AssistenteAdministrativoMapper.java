
package br.ind.cmil.gestao.assistente.mapper;

import br.ind.cmil.gestao.assistente.domain.AssistenteAdministrativo;
import br.ind.cmil.gestao.assistente.model.AssistenteAdministrativoDTO;
import br.ind.cmil.gestao.usuario.domain.Usuario;
import org.springframework.stereotype.Component;

/**
 *
 * @author ti
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


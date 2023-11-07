
package br.ind.cmil.gestao.model.dto.mappers;

import br.ind.cmil.gestao.dto.AssistenteAdministrativoDTO;
import br.ind.cmil.gestao.dto.UsuarioRequest;
import br.ind.cmil.gestao.model.entidades.AssistenteAdministrativo;
import br.ind.cmil.gestao.model.entidades.Usuario;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class AssistenteAdministrativoMapper {
     public AssistenteAdministrativoDTO toDTO(AssistenteAdministrativo assistente) {
        UsuarioMapper um = new UsuarioMapper();
        UsuarioRequest usuario = um.toDTO(assistente.getUsuario());
        return new AssistenteAdministrativoDTO(assistente.getId(), assistente.getNome(), usuario);
    }

    public AssistenteAdministrativo toEntity(AssistenteAdministrativoDTO dto) {

        AssistenteAdministrativo assistente = new AssistenteAdministrativo();
        assistente.setId(dto.id());       
        assistente.setNome(dto.nome());
        UsuarioMapper um = new UsuarioMapper();
        Usuario usuario = um.toEntity(dto.usuario());
        assistente.setUsuario(usuario);
        // u.setPerfis(perfis(dto.perfis()));
        return assistente;
    }
}


package br.ind.cmil.gestao.model.dto.mappers;

import br.ind.cmil.gestao.exceptions.ObjectNotFoundException;
import br.ind.cmil.gestao.model.dto.AuxiliarAdministrativoDTO;
import br.ind.cmil.gestao.model.dto.UsuarioRequest;
import br.ind.cmil.gestao.model.entidades.AuxiliarAdministrativo;
import br.ind.cmil.gestao.model.entidades.Usuario;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class AuxiliarAdministrativoMapper {

    public AuxiliarAdministrativoDTO toDTO(AuxiliarAdministrativo auxiliar) {
        UsuarioMapper um = new UsuarioMapper();
        UsuarioRequest usuario = um.toDTO(auxiliar.getUsuario());
        return new AuxiliarAdministrativoDTO(auxiliar.getId(), auxiliar.getNome(), usuario);
    }

    public AuxiliarAdministrativo toEntity(AuxiliarAdministrativoDTO dto) {

        AuxiliarAdministrativo auxiliar = new AuxiliarAdministrativo();
        auxiliar.setId(dto.id());       
        auxiliar.setNome(dto.nome());
        UsuarioMapper um = new UsuarioMapper();
        Usuario usuario = um.toEntity(dto.usuario());
        auxiliar.setUsuario(usuario);
        // u.setPerfis(perfis(dto.perfis()));
        return auxiliar;
    }
    
}

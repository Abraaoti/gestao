
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

    public AuxiliarAdministrativoDTO toDTO(AuxiliarAdministrativo a) {
        UsuarioMapper um = new UsuarioMapper();
        UsuarioRequest usuario = um.toDTO(a.getUsuario());
        return new AuxiliarAdministrativoDTO(a.getId(), a.getNome(), usuario);
    }

    public AuxiliarAdministrativo toEntity(AuxiliarAdministrativoDTO dto) {

        AuxiliarAdministrativo a = new AuxiliarAdministrativo();
        a.setId(dto.id());
        if (a.getId() != null) {
            throw new ObjectNotFoundException("Usuário já consta no nosso banco de dados!");
        }
        a.setNome(dto.nome());
        UsuarioMapper um = new UsuarioMapper();
        Usuario usuario = um.toEntity(dto.usuario());
        a.setUsuario(usuario);
        // u.setPerfis(perfis(dto.perfis()));
        return a;
    }
    
}

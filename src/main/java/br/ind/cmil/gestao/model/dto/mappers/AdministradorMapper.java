
package br.ind.cmil.gestao.model.dto.mappers;

import br.ind.cmil.gestao.exceptions.ObjectNotFoundException;
import br.ind.cmil.gestao.model.dto.AdministradorDTO;
import br.ind.cmil.gestao.model.dto.UsuarioRequest;
import br.ind.cmil.gestao.model.entidades.Administrador;
import br.ind.cmil.gestao.model.entidades.Usuario;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class AdministradorMapper {

    public AdministradorDTO toDTO(Administrador a) {
        UsuarioMapper um = new UsuarioMapper();
        UsuarioRequest usuario = um.toDTO(a.getUsuario());
        return new AdministradorDTO(a.getId(), a.getNome(), usuario);
    }

    public Administrador toEntity(AdministradorDTO dto) {

        Administrador a = new Administrador();
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

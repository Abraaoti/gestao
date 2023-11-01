package br.ind.cmil.gestao.model.dto.mappers;

import br.ind.cmil.gestao.exceptions.ObjectNotFoundException;
import br.ind.cmil.gestao.model.dto.AdministradorDTO;
import br.ind.cmil.gestao.model.dto.UsuarioRequest;
import br.ind.cmil.gestao.model.entidades.Administrador;
import br.ind.cmil.gestao.model.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class AdministradorMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;

    public AdministradorDTO toDTO(Administrador administrador) {

        UsuarioRequest usuario = this.usuarioMapper.toDTO(administrador.getUsuario());
        return new AdministradorDTO(administrador.getId(), administrador.getNome(), usuario);
    }

    public Administrador toEntity(AdministradorDTO dto) {
        Administrador administrador = new Administrador();
        administrador.setId(dto.id());
        administrador.setNome(dto.nome());
        Usuario usuario = this.usuarioMapper.toEntity(dto.usuario());
        administrador.setUsuario(usuario);
        // u.setPerfis(perfis(dto.perfis()));
        return administrador;
    }
}

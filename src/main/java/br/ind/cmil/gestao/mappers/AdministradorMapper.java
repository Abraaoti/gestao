package br.ind.cmil.gestao.mappers;

import br.ind.cmil.gestao.model.dto.AdministradorDTO;
import br.ind.cmil.gestao.domain.Administrador;
import br.ind.cmil.gestao.domain.Usuario;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class AdministradorMapper {
    
    public AdministradorDTO toDTO(Administrador administrador) {
        Long usuario = (administrador.getUsuario().getId() == null) ? null : administrador.getUsuario().getId();
        return new AdministradorDTO(administrador.getId(), administrador.getNome(), usuario);
    }
    
    public Administrador toEntity(AdministradorDTO dto) {
        Administrador administrador = new Administrador();
        administrador.setNome(dto.nome());
        administrador.setUsuario(new Usuario(dto.id()));
        return administrador;
    }
}

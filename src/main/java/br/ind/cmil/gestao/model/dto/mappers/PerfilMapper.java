package br.ind.cmil.gestao.model.dto.mappers;

import br.ind.cmil.gestao.model.dto.PerfilDTO;
import br.ind.cmil.gestao.model.entidades.Perfil;
import br.ind.cmil.gestao.model.enums.TipoPerfil;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class PerfilMapper {

    public PerfilDTO toDTO(Perfil p) {
        if (p == null) {
            return null;
        }

        return new PerfilDTO(p.getId(), p.getTp().getValue());
    }

    public Perfil toEntity(PerfilDTO dto) {
        if (dto == null) {
            return null;
        }
        Perfil p = new Perfil();
        p.setTp(TipoPerfil.convertPerfilValue(dto.p()));
        return p;
    }

}

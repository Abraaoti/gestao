
package br.ind.cmil.gestao.perfil.mapper;

import br.ind.cmil.gestao.perfil.domain.Perfil;
import br.ind.cmil.gestao.perfil.enums.TipoPerfil;
import br.ind.cmil.gestao.perfil.model.PerfilDTO;
import org.springframework.stereotype.Component;

/**
 *
 * @author ti
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


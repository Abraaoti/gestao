package br.ind.cmil.gestao.mappers;

import br.ind.cmil.gestao.model.dto.AuxiliarAdministrativoDTO;
import br.ind.cmil.gestao.domain.AuxiliarAdministrativo;
import br.ind.cmil.gestao.domain.Usuario;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class AuxiliarAdministrativoMapper {

    public AuxiliarAdministrativoDTO toDTO(AuxiliarAdministrativo auxiliar) {
        Long usuario = (auxiliar.getUsuario().getId() == null) ? null : auxiliar.getUsuario().getId();
        return new AuxiliarAdministrativoDTO(auxiliar.getId(), auxiliar.getNome(), usuario);
    }

    public AuxiliarAdministrativo toEntity(AuxiliarAdministrativoDTO dto, AuxiliarAdministrativo auxiliar) {
        auxiliar.setNome(dto.nome());
        auxiliar.setUsuario(new Usuario(dto.id()));
        return auxiliar;
    }

}

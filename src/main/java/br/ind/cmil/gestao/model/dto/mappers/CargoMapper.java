package br.ind.cmil.gestao.model.dto.mappers;

import br.ind.cmil.gestao.dto.CargoDTO;
import br.ind.cmil.gestao.model.entidades.Cargo;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class CargoMapper {

    public CargoDTO toDTO(Cargo c) {
        if (c.getId() == null) {
            return null;
        }
        return new CargoDTO(c.getId(), c.getNome());
    }

    public Cargo toEntity(CargoDTO dto) {
        if (dto == null) {
            return null;
        }
        Cargo c = new Cargo();
        c.setId(dto.id());
        c.setNome(dto.nome());
        return c;
    }

    

   
}

package br.ind.cmil.gestao.mappers;

import br.ind.cmil.gestao.model.dto.CargoDTO;
import br.ind.cmil.gestao.domain.Cargo;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class CargoMapper {

    public CargoDTO toDTO(Cargo c) {
        return new CargoDTO(c.getId(), c.getNome());
    }

    public Cargo toEntity(CargoDTO dto) {
        Cargo cargo = new Cargo();
        cargo.setNome(dto.nome());
        cargo.setId(dto.id());
        return cargo;
    }

}

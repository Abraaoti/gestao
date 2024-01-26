package br.ind.cmil.gestao.cargo.mapprer;

import br.ind.cmil.gestao.cargo.domain.Cargo;
import br.ind.cmil.gestao.cargo.model.CargoDTO;
import org.springframework.stereotype.Component;

/**
 *
 * @author ti
 */
@Component
public class CargoMapper {

    public CargoDTO toDTO(Cargo c) {
        return new CargoDTO(c.getId(), c.getNome(), c.getSalario());
    }

    public Cargo toEntity(CargoDTO dto) {
        Cargo cargo = new Cargo();
        cargo.setNome(dto.nome());
        cargo.setSalario(dto.salario());
        cargo.setId(dto.id());
        return cargo;
    }

}

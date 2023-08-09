package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.exceptions.CargoException;
import br.ind.cmil.gestao.model.dto.CargoDTO;
import br.ind.cmil.gestao.model.dto.mappers.CargoMapper;
import br.ind.cmil.gestao.model.entidades.Cargo;
import br.ind.cmil.gestao.model.repositorys.ICargoRepository;
import br.ind.cmil.gestao.model.services.interfaces.ICargoService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
@Service
public class CargoServiceImp implements ICargoService {

    private final ICargoRepository cr;
    private final CargoMapper cm;

    public CargoServiceImp(ICargoRepository cr, CargoMapper cm) {
        this.cr = cr;
        this.cm = cm;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CargoDTO> list(Pageable pageable) {
        return cr.searchAll(pageable).stream().map(cm::toDTO).collect(Collectors.toList());
    }

    @Override
    public Set<CargoDTO> lista() {
        return cr.searchAll().stream().map(cm::toDTO).collect(Collectors.toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public CargoDTO findById(Long id) {
        return cr.findById(id).map(cm::toDTO).orElseThrow(() -> new CargoException(String.valueOf(id), "Este id não consta no bd! "));
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public CargoDTO create(CargoDTO c) {
        Cargo cargo = cm.toEntity(c);
        c.id();
        validarAtributos(cargo);

        if (cargo.getId() == null) {

            return cm.toDTO(cr.save(cargo));
        }

        return update(c);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    protected CargoDTO update(CargoDTO c) {
        Optional<Cargo> cargo = cr.findById(c.id());
        if (cargo.isEmpty()) {
            return null;
        }

        var ca = cargo.get();
        ca.setNome(c.nome());
        ca.setId(c.id());

        return cm.toDTO(cr.save(ca));

    }

    @Override
    public void delete(Long id) {
        cr.delete(cr.findById(id).orElseThrow(() -> new CargoException(String.valueOf(id), "Este id não consta no bd! ")));
    }

    private void validarAtributos(Cargo c) {
        Optional<Cargo> cargo = cr.findByNome(c.getNome());
        if (cargo.isPresent() && !Objects.equals(cargo.get().getId(), c.getId())) {
            throw new DataIntegrityViolationException("cargo já cadastro no sistema!");
        }

    }

    @Override
    public Cargo findByNome(String nome) {
        return cr.findByNome(nome).orElseThrow(() -> new CargoException(nome, "Este cargo não consta no bd! "));
    }

}

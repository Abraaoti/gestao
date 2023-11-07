package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.dto.CargoDTO;
import br.ind.cmil.gestao.exceptions.CargoException;
import br.ind.cmil.gestao.model.datatables.Datatables;
import br.ind.cmil.gestao.model.datatables.DatatablesColunas;
import br.ind.cmil.gestao.model.entidades.Cargo;
import br.ind.cmil.gestao.model.repositorys.ICargoRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import br.ind.cmil.gestao.model.services.interfaces.CargoService;

/**
 *
 * @author abraao
 */
@Service
public class CargoServiceImp implements CargoService {

    private final ICargoRepository cr;
    private final Datatables datatables;

    public CargoServiceImp(ICargoRepository cr, Datatables datatables) {
        this.cr = cr;
        this.datatables = datatables;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cargo> lista() {
        return cr.searchAll().stream().collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Cargo findById(Long id) {
        return cr.findById(id).orElseThrow(() -> new CargoException(String.valueOf(id), "Este id não consta no bd! "));
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void salvar(Cargo cargo) {
        cargo.getId();
        validarAtributos(cargo);

        if (cargo.getId() == null) {

             cr.save(cargo);
        }

         update(cargo);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    protected Cargo update(Cargo c) {
        Optional<Cargo> cargo = cr.findById(c.getId());
        if (cargo.isEmpty()) {
            return null;
        }

        var ca = cargo.get();
        ca.setNome(c.getNome());
        ca.setId(c.getId());

        return cr.save(ca);

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

    @Transactional(readOnly = true)
    @Override
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.CARGO);
        Page<Cargo> page = datatables.getSearch().isEmpty() ? cr.findAll(datatables.getPageable())
                : cr.searchAll(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

}

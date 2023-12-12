package br.ind.cmil.gestao.services.impl;

import br.ind.cmil.gestao.exceptions.CargoException;
import br.ind.cmil.gestao.datatables.Datatables;
import br.ind.cmil.gestao.datatables.DatatablesColunas;
import br.ind.cmil.gestao.domain.Cargo;
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
import br.ind.cmil.gestao.services.CargoService;
import br.ind.cmil.gestao.repositorys.CargoRepository;
import br.ind.cmil.gestao.util.CustomCollectors;
import org.springframework.data.domain.Sort;

/**
 *
 * @author abraao
 */
@Service
public class CargoServiceImp implements CargoService {

    private final CargoRepository cr;
    private final Datatables datatables;

    public CargoServiceImp(CargoRepository cr, Datatables datatables) {
        this.cr = cr;
        this.datatables = datatables;
    }
   
    
      

    @Override
    @Transactional(readOnly = true)
    public Map<Long, String> cargos() {
        return cr.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Cargo::getId, Cargo::getNome));

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
    
    /** public String getReferencedWarning(final Long id) {
        final Cargo cargo = cr.findById(id)
                .orElseThrow(NotFoundException::new);
        final Funcionario cargoFuncionario = funcionarioService.findFirstByCargo(cargo);
        if (cargoFuncionario != null) {
            return WebUtils.getMessage("cargo.funcionario.cargo.referenced", cargoFuncionario.getId());
        }
        return null;
    }**/

}

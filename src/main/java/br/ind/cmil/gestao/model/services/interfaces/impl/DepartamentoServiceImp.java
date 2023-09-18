package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.exceptions.DepartamentoException;
import br.ind.cmil.gestao.model.datatables.Datatables;
import br.ind.cmil.gestao.model.datatables.DatatablesColunas;
import br.ind.cmil.gestao.model.dto.DepartamentoDTO;
import br.ind.cmil.gestao.model.dto.mappers.DepartamentoMapper;
import br.ind.cmil.gestao.model.entidades.Departamento;
import br.ind.cmil.gestao.model.repositorys.IDepartamentoRepository;
import br.ind.cmil.gestao.model.services.interfaces.IDepartamentoService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
@Service
public class DepartamentoServiceImp implements IDepartamentoService {

    private final IDepartamentoRepository dr;
    private final DepartamentoMapper dm;
    private final Datatables datatables;

    public DepartamentoServiceImp(IDepartamentoRepository dr, DepartamentoMapper dm, Datatables datatables) {
        this.dr = dr;
        this.dm = dm;
        this.datatables = datatables;
    }

    @Override
      @Transactional(readOnly = true)
    public List<DepartamentoDTO> list(Pageable pageable) {
        return dr.searchAll(pageable).stream().map(dm::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DepartamentoDTO findById(Long id) {
        return dr.findById(id).map(dm::toDTO).orElseThrow(() -> new DepartamentoException(String.valueOf(id), "Este id não consta no bd! "));
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public DepartamentoDTO create(DepartamentoDTO d) {
        if (d.id() == null) {
            return dm.toDTO(dr.save(dm.toEntity(d)));
        }
        return update(d);
    }

    @Override
    @Transactional(readOnly = false)
    public DepartamentoDTO update(DepartamentoDTO d) {
        return dr.findById(d.id())
                .map(recordFound -> {
                    recordFound.setNome(d.nome());
                    recordFound.setId(d.id());
                    return dm.toDTO(dr.save(recordFound));
                }).orElseThrow(() -> new DepartamentoException(String.valueOf(d.id()), "Este id não consta no bd! "));
    }

    @Override
      @Transactional(readOnly = false)
    public void delete(Long id) {
        dr.delete(dr.findById(id).orElseThrow(() -> new DepartamentoException(String.valueOf(id), "Este id não consta no bd! ")));
    }

    @Override
      @Transactional(readOnly = true)
    public Departamento findByNome(String nome) {
        return dr.findByNome(nome).orElseThrow(() -> new DepartamentoException(nome, "Este departamento não consta no bd! "));
    }

    @Override
      @Transactional(readOnly = true)
    public Set<DepartamentoDTO> lista() {
        return dr.searchAll().stream().map(dm::toDTO).collect(Collectors.toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.DEPARTAMENTO);
        Page<?> page = datatables.getSearch().isEmpty() ? dr.findAll(datatables.getPageable())
                : dr.searchAll(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

}

package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.exceptions.DepartamentoException;
import br.ind.cmil.gestao.model.datatables.Datatables;
import br.ind.cmil.gestao.model.datatables.DatatablesColunas;
import br.ind.cmil.gestao.model.entidades.Departamento;
import br.ind.cmil.gestao.model.repositorys.IDepartamentoRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ind.cmil.gestao.model.services.interfaces.DepartamentoService;

/**
 *
 * @author abraao
 */
@Service
public class DepartamentoServiceImp implements DepartamentoService {

    private final IDepartamentoRepository departamentoRepository;
    private final Datatables datatables;

    public DepartamentoServiceImp(IDepartamentoRepository departamentoRepository, Datatables datatables) {
        this.departamentoRepository = departamentoRepository;
        this.datatables = datatables;
    }

   

    @Override
      @Transactional(readOnly = true)
    public List<Departamento> lista() {
        return departamentoRepository.searchAll().stream().collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Departamento findById(Long id) {
        return departamentoRepository.findById(id).orElseThrow(() -> new DepartamentoException(String.valueOf(id), "Este id não consta no bd! "));
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void salvar(Departamento d) {
        if (d.getId() == null) {
            departamentoRepository.save(d);
        }
         update(d);
    }

    
    @Transactional(readOnly = false)
    protected Departamento update(Departamento departamento) {
        return departamentoRepository.findById(departamento.getId())
                .map(recordFound -> {
                    recordFound.setNome(departamento.getNome());
                    recordFound.setId(departamento.getId());
                    return departamentoRepository.save(recordFound);
                }).orElseThrow(() -> new DepartamentoException(String.valueOf(departamento.getId()), "Este id não consta no bd! "));
    }

    @Override
      @Transactional(readOnly = false)
    public void delete(Long id) {
        departamentoRepository.delete(departamentoRepository.findById(id).orElseThrow(() -> new DepartamentoException(String.valueOf(id), "Este id não consta no bd! ")));
    }

    @Override
      @Transactional(readOnly = true)
    public Departamento findByNome(String nome) {
        return departamentoRepository.findByNome(nome).orElseThrow(() -> new DepartamentoException(nome, "Este departamento não consta no bd! "));
    }

    
    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.DEPARTAMENTO);
        Page<Departamento> page = datatables.getSearch().isEmpty() ? departamentoRepository.findAll(datatables.getPageable())
                : departamentoRepository.searchAll(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

}


package br.ind.cmil.gestao.departamento.service.imp;

import br.ind.cmil.gestao.datatables.Datatables;
import br.ind.cmil.gestao.datatables.DatatablesColunas;
import br.ind.cmil.gestao.departamento.domain.Departamento;
import br.ind.cmil.gestao.departamento.mapper.DepartamentoMapper;
import br.ind.cmil.gestao.departamento.model.DepartamentoDTO;
import br.ind.cmil.gestao.departamento.respository.DepartamentoRepository;
import br.ind.cmil.gestao.departamento.service.DepartamentoService;
import br.ind.cmil.gestao.exceptions.DepartamentoException;
import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import br.ind.cmil.gestao.funcionario.repository.FuncionarioRepository;
import br.ind.cmil.gestao.util.CustomCollectors;
import br.ind.cmil.gestao.util.NotFoundException;
import br.ind.cmil.gestao.util.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ti
 */
@Service
public class DepartamentoServiceImp implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final DepartamentoMapper departamentoMapper;
    private final Datatables datatables;

    public DepartamentoServiceImp(DepartamentoRepository departamentoRepository, DepartamentoMapper departamentoMapper, Datatables datatables) {
        this.departamentoRepository = departamentoRepository;
        this.departamentoMapper = departamentoMapper;
        this.datatables = datatables;
    }

  

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Long salvar(DepartamentoDTO departamentoDTO) {

        if (departamentoDTO.id() == null) {
            validar(departamentoDTO);
            return departamentoRepository.save(departamentoMapper.toEntity(departamentoDTO)).getId();

        }

        return update(departamentoDTO).getId();
    }

    @Transactional(readOnly = false)
    private Departamento update(DepartamentoDTO departamentoDTO) {
        Departamento departamento = departamentoRepository.findById(departamentoDTO.id())
                .map(recordFound -> {
                    recordFound.setNome(departamentoDTO.nome());
                    recordFound.setId(departamentoDTO.id());
                    return departamentoRepository.save(recordFound);
                }).orElseThrow(() -> new DepartamentoException(String.valueOf(departamentoDTO.id()), "Este id não consta no bd! "));

        return departamentoRepository.save(departamento);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<Long, String> departamentos() {
        return departamentoRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Departamento::getId, Departamento::getNome));

    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartamentoDTO> lista() {
        List<Departamento> departamentos = departamentoRepository.findAll(Sort.by("id"));
        //return departamentos.stream().map(departamentoMapper::dto()).collect(Collectors.toList());
        return departamentos.stream().map(departamentoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DepartamentoDTO findById(Long id) {
        return departamentoMapper.toDTO(departamentoRepository.findById(id).orElseThrow(() -> new DepartamentoException(String.valueOf(id), "Este id não consta no bd! ")));
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        departamentoRepository.delete(departamentoRepository.findById(id).orElseThrow(() -> new DepartamentoException(String.valueOf(id), "Este id não consta no bd! ")));
    }

    @Override
    @Transactional(readOnly = true)
    public Departamento findByNome(String nome) {
        //return departamentoMapper.toDTO(departamentoRepository.findByNome(nome).orElseThrow(() -> new DepartamentoException(nome, "Este departamento não consta no bd! ")));
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

    private void validar(DepartamentoDTO departamento) {
        Optional<Departamento> administrador = departamentoRepository.findByNome(departamento.nome());
        if (administrador.isPresent() && !Objects.equals(administrador.get().getId(), departamento.id())) {
            throw new DataIntegrityViolationException("Departamento já cadastro no sistema!");
        }

    }

   

}


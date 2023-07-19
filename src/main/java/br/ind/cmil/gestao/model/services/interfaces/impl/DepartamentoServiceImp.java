package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.exceptions.DepartamentoException;
import br.ind.cmil.gestao.model.dto.DepartamentoDTO;
import br.ind.cmil.gestao.model.dto.mappers.DepartamentoMapper;
import br.ind.cmil.gestao.model.entidades.Departamento;
import br.ind.cmil.gestao.model.repositorys.IDepartamentoRepository;
import br.ind.cmil.gestao.model.services.interfaces.IDepartamentoService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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

    public DepartamentoServiceImp(IDepartamentoRepository dr, DepartamentoMapper dm) {
        this.dr = dr;
        this.dm = dm;
    }

    @Override
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
    public DepartamentoDTO create(DepartamentoDTO dep) {
        return dm.toDTO(dr.save(dm.toEntity(dep)));
    }

    @Override
    @Transactional(readOnly = true)
    public DepartamentoDTO update(Long id, DepartamentoDTO d) {
        return dr.findById(id)
                .map(recordFound -> {
                    recordFound.setNome(d.nome());
                    recordFound.setId(d.id());
                    return dm.toDTO(dr.save(recordFound));
                }).orElseThrow(() -> new DepartamentoException(String.valueOf(id), "Este id não consta no bd! "));
    }

    @Override
    public void delete(Long id) {
        dr.delete(dr.findById(id).orElseThrow(() -> new DepartamentoException(String.valueOf(id), "Este id não consta no bd! ")));
    }

    @Override
    public Departamento findByNome(String nome) {
     return dr.findByNome(nome).orElseThrow(() -> new DepartamentoException(nome, "Este departamento não consta no bd! "));
    }

    @Override
    public Set<DepartamentoDTO> lista() {
        return dr.searchAll().stream().map(dm::toDTO).collect(Collectors.toSet());
    }

   

}

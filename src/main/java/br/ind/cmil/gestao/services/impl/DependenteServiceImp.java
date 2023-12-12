package br.ind.cmil.gestao.services.impl;

import br.ind.cmil.gestao.model.dto.DependenteDTO;
import br.ind.cmil.gestao.domain.Dependente;
import br.ind.cmil.gestao.mappers.DependenteMapper;
import br.ind.cmil.gestao.repositorys.DependenteRepository;
import br.ind.cmil.gestao.repositorys.FuncionarioRepository;
import br.ind.cmil.gestao.services.DependenteService;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author abraao
 */
@Service
public class DependenteServiceImp implements DependenteService {

    private final DependenteRepository dependenteRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final DependenteMapper dependenteMapper;

    public DependenteServiceImp(DependenteRepository dependenteRepository, FuncionarioRepository funcionarioRepository, DependenteMapper dependenteMapper) {
        this.dependenteRepository = dependenteRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.dependenteMapper = dependenteMapper;
    }

    @Override
    public List<DependenteDTO> findAll() {
        final List<Dependente> dependentes = dependenteRepository.findAll(Sort.by("id"));
        return dependentes.stream().map(dependenteMapper::toDTO).toList();
    }

    @Override
    public DependenteDTO get(Long id) {
        return dependenteRepository.findById(id).map(dependenteMapper::toDTO).get();
    }

    @Override
    public Long create(DependenteDTO dependenteDTO) {
        if (dependenteDTO.id() == null) {
            validar(dependenteDTO);
            dependenteRepository.save(dependenteMapper.toEntity(dependenteDTO)).getId();
        }

        return null;// dependenteRepository.save(dependenteDTO).getId();
    }

    private void update(DependenteDTO dependenteDTO) {
        Dependente dependente = dependenteRepository.findById(dependenteDTO.id()).get();

        dependenteRepository.save(dependente);
    }

    @Override
    public void delete(Long id) {
        dependenteRepository.deleteById(id);
    }

    @Override
    public boolean nomeExists(final String nome) {
        return dependenteRepository.existsByNomeIgnoreCase(nome);
    }

    private void validar(DependenteDTO dependenteDTO) {
    }
}

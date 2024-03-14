package br.ind.cmil.gestao.dependente.services.imp;

import br.ind.cmil.gestao.dependente.domain.Dependente;
import br.ind.cmil.gestao.dependente.mapper.DependenteMapper;
import br.ind.cmil.gestao.dependente.model.DependenteDTO;
import br.ind.cmil.gestao.dependente.repository.DependenteRepository;
import br.ind.cmil.gestao.dependente.services.DependenteService;
import br.ind.cmil.gestao.funcionario.repository.FuncionarioRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author ti
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

  

    private void validar(DependenteDTO dependenteDTO) {
    }
}

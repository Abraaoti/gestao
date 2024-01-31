package br.ind.cmil.gestao.ponto.service.impl;

import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import br.ind.cmil.gestao.funcionario.repository.FuncionarioRepository;
import br.ind.cmil.gestao.ponto.domain.Ponto;
import br.ind.cmil.gestao.ponto.model.PontoDTO;
import br.ind.cmil.gestao.ponto.model.mapper.PontoMapper;
import br.ind.cmil.gestao.ponto.repositorys.PontoRepository;
import br.ind.cmil.gestao.ponto.service.PontoService;
import br.ind.cmil.gestao.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author ti
 */
@Service
public class PontoServiceImp implements PontoService {

    private final PontoRepository pontoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final PontoMapper pontoMapper;
    // private final Datatables datatables;

    public PontoServiceImp(PontoRepository pontoRepository, FuncionarioRepository funcionarioRepository, PontoMapper pontoMapper) {
        this.pontoRepository = pontoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.pontoMapper = pontoMapper;
    }

    @Override
    public List<PontoDTO> getPontos() {
        final List<Ponto> pontoes = pontoRepository.findAll(Sort.by("id"));
        return pontoes.stream().map(ponto -> pontoMapper.toDTO(ponto)).toList();
    }

    @Override
    public PontoDTO buscarPorId(Long id) {
        return pontoRepository.findById(id)
                .map(ponto -> pontoMapper.toDTO(ponto))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Long create(PontoDTO pontoDTO) {
        Ponto ponto = pontoMapper.toEntity(pontoDTO);
        if (ponto.getId() == null) {
            Funcionario funcinario = funcionarioRepository.findById(pontoDTO.funcionario()).get();
            ponto.setFuncionario(funcinario);
            return pontoRepository.save(ponto).getId();
        }
        return null;
    }

    @Override
    public void update(Long id, PontoDTO pontoDTO) {
        final Ponto ponto = pontoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        // mapToEntity(pontoDTO, ponto);
        pontoRepository.save(ponto);
    }

    @Override
    public void delete(Long id) {
        pontoRepository.deleteById(id);
    }

    @Override
    public PontoDTO abrirForm(Long funcionarioId, PontoDTO pontoDTO) {
        Ponto ponto = pontoMapper.toEntity(pontoDTO);
        if (ponto.getId() != null) {
            ponto = pontoRepository.findPonto(funcionarioId);
            return pontoMapper.toDTO(ponto);
        }
        ponto.setFuncionario(new Funcionario(funcionarioId));
        return pontoMapper.toDTO(ponto);
    }

}

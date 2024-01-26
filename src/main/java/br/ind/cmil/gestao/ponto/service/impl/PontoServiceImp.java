package br.ind.cmil.gestao.ponto.service.impl;

import br.ind.cmil.gestao.ponto.domain.Ponto;
import br.ind.cmil.gestao.ponto.model.PontoDTO;
import br.ind.cmil.gestao.ponto.model.mapper.PontoMapper;
import br.ind.cmil.gestao.ponto.repositorys.PontoRepository;
import br.ind.cmil.gestao.ponto.service.PontoService;
import br.ind.cmil.gestao.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;

/**
 *
 * @author ti
 */
public class PontoServiceImp implements PontoService {

    private final PontoRepository pontoRepository;
    // private final FuncionarioService funcionarioService;
    private final PontoMapper pontoMapper;
    // private final Datatables datatables;

    public PontoServiceImp(PontoRepository pontoRepository, PontoMapper pontoMapper) {
        this.pontoRepository = pontoRepository;
        this.pontoMapper = pontoMapper;
    }

    @Override
    public List<PontoDTO> getPontos() {
        final List<Ponto> pontoes = pontoRepository.findAll(Sort.by("id"));
        return pontoes.stream()
                .map(ponto -> pontoMapper.toDTO(ponto))
                .toList();
    }

    @Override
    public PontoDTO buscarPorId(Long id) {
        return pontoRepository.findById(id)
                .map(ponto -> pontoMapper.toDTO(ponto))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Long create(PontoDTO pontoDTO) {
        return pontoRepository.save(pontoMapper.toEntity(pontoDTO)).getId();
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

}

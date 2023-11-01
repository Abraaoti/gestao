package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.exceptions.ObjectNotFoundException;
import br.ind.cmil.gestao.model.datatables.Datatables;
import br.ind.cmil.gestao.model.datatables.DatatablesColunas;
import br.ind.cmil.gestao.model.dto.LotacaoDTO;
import br.ind.cmil.gestao.model.dto.mappers.LotacaoMapper;
import br.ind.cmil.gestao.model.entidades.Lotacao;
import br.ind.cmil.gestao.model.repositorys.LotacaoRepository;
import br.ind.cmil.gestao.model.services.interfaces.LotacaoService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
@Service
public class LotacaoServiceImp implements LotacaoService {

    private final LotacaoRepository lotacaoRepository;
    private final LotacaoMapper lotacaoMapper;
    private final Datatables datatables;

    public LotacaoServiceImp(LotacaoRepository lotacaoRepository, LotacaoMapper lotacaoMapper, Datatables datatables) {
        this.lotacaoRepository = lotacaoRepository;
        this.lotacaoMapper = lotacaoMapper;
        this.datatables = datatables;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(LotacaoDTO lotacao) {
        lotacaoRepository.save(lotacaoMapper.toEntity(lotacao));
    }

    @Transactional(readOnly = false)
    public LotacaoDTO update(LotacaoDTO lotacao) {
        return lotacaoRepository.findById(lotacao.id())
                .map(recordFound -> {
                    recordFound.setNome(lotacao.nome());
                    //recordFound.setAdministrador(lotacao.administrador());
                    return lotacaoMapper.toDTO(lotacaoRepository.save(recordFound));
                }).orElseThrow(() -> new ObjectNotFoundException(String.valueOf(lotacao.id()) + "Este id não consta no bd! "));
    }

    @Transactional(readOnly = true)
    @Override
    public List<LotacaoDTO> lista() {
        return lotacaoRepository.findAllByOrderByNomeAscIdDesc().stream().map(lotacaoMapper::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public LotacaoDTO findById(Long id) {
        return lotacaoRepository.findById(id).map(lotacaoMapper::toDTO).orElseThrow(() -> new ObjectNotFoundException(String.valueOf(id) + "Este id não consta no bd! "));
    }

    @Override
    public void delete(Long id) {
        Optional<Lotacao> locacao = lotacaoRepository.findById(id);
        lotacaoRepository.delete(locacao.get());
    }

    @Override
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.LOTACAO);
        Page<Lotacao> page = datatables.getSearch().isEmpty() ? lotacaoRepository.findAll(datatables.getPageable())
                : lotacaoRepository.searchAll(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

}

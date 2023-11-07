package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.dto.LotacaoDTO;
import br.ind.cmil.gestao.exceptions.ObjectNotFoundException;
import br.ind.cmil.gestao.model.datatables.Datatables;
import br.ind.cmil.gestao.model.datatables.DatatablesColunas;
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
    private final Datatables datatables;

    public LotacaoServiceImp(LotacaoRepository lotacaoRepository, Datatables datatables) {
        this.lotacaoRepository = lotacaoRepository;
        this.datatables = datatables;
    }

  

    @Transactional(readOnly = false)
    @Override
    public void salvar(Lotacao lotacao) {
        if (lotacao.getId() == null) {            
        lotacaoRepository.save(lotacao);
        }
        update(lotacao);
    }

    @Transactional(readOnly = false)
    public Lotacao update(Lotacao lotacao) {
        return lotacaoRepository.findById(lotacao.getId())
                .map(recordFound -> {
                    recordFound.setNome(lotacao.getNome());
                    //recordFound.setAdministrador(lotacao.administrador());
                    return lotacaoRepository.save(recordFound);
                }).orElseThrow(() -> new ObjectNotFoundException(String.valueOf(lotacao.getId()) + "Este id não consta no bd! "));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Lotacao> lista() {
        return lotacaoRepository.findAllByOrderByNomeAscIdDesc().stream().collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Lotacao findById(Long id) {
        return lotacaoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(String.valueOf(id) + "Este id não consta no bd! "));
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

    @Override
    public Lotacao findByNome(String nome) {
       return lotacaoRepository.findByNome(nome).orElseThrow(() -> new ObjectNotFoundException(nome + "Este id não consta no bd! "));
    }


}

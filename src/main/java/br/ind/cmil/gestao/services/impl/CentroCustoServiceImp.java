package br.ind.cmil.gestao.services.impl;

import br.ind.cmil.gestao.datatables.Datatables;
import br.ind.cmil.gestao.datatables.DatatablesColunas;
import br.ind.cmil.gestao.model.dto.CentroCustoDTO;
import br.ind.cmil.gestao.domain.CentroCusto;
import br.ind.cmil.gestao.domain.Funcionario;
import br.ind.cmil.gestao.mappers.CentroCustoMapper;
import br.ind.cmil.gestao.repositorys.CentroCustoRepository;
import br.ind.cmil.gestao.repositorys.FuncionarioRepository;
import br.ind.cmil.gestao.services.CentroCustoService;
import br.ind.cmil.gestao.util.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author abraao
 */
@Service
public class CentroCustoServiceImp implements CentroCustoService {
    
    private final CentroCustoRepository centroCustoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final CentroCustoMapper centroCustoMapper;
    private final Datatables datatables;
    
    public CentroCustoServiceImp(CentroCustoRepository centroCustoRepository, FuncionarioRepository funcionarioRepository, CentroCustoMapper centroCustoMapper, Datatables datatables) {
        this.centroCustoRepository = centroCustoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.centroCustoMapper = centroCustoMapper;
        this.datatables = datatables;
    }
    
    @Override
    public List<CentroCustoDTO> findAll() {
        
        List<CentroCusto> centroCustos = centroCustoRepository.findAll(Sort.by("id"));
        return centroCustos.stream().map(centroCustoMapper::toDTO).toList();
    }
    
    @Override
    public Long create(CentroCustoDTO centroCustoDTO) {
        CentroCusto centroCusto = centroCustoMapper.toEntity(centroCustoDTO);
        
        validarAtributos(centroCusto);
        if (centroCustoDTO.id() == null) {
          return centroCustoRepository.save(centroCusto).getId();
        }
        
        return update(centroCustoDTO);
    }
    
  
    private Long update(CentroCustoDTO centroCustoDTO) {
        CentroCusto centroCusto = centroCustoRepository.findById(centroCustoDTO.id()).get();
        centroCusto.setNome(centroCustoDTO.nome());
        centroCusto.setId(centroCustoDTO.id());
       return centroCustoRepository.save(centroCusto).getId();
    }
    
    @Override
    public CentroCustoDTO get(Long id) {
        return centroCustoRepository.findById(id).map(centroCustoMapper::toDTO).get();
    }
    
    private void validarAtributos(CentroCusto c) {
        Optional<CentroCusto> centroCusto = centroCustoRepository.findByNome(c.getNome());
        if (centroCusto.isPresent() && !Objects.equals(centroCusto.get().getId(), c.getId())) {
            throw new DataIntegrityViolationException("nome já cadastro no sistema!");
        }
        
    }
    
    @Override
    public void delete(Long id) {
        centroCustoRepository.deleteById(id);
    }
    
    @Override
    public boolean nomeExists(String nome) {
        return centroCustoRepository.existsByNomeIgnoreCase(nome);
    }
    
    @Override
    public String getReferencedWarning(Long id) {
        CentroCusto centroCusto = centroCustoRepository.findById(id).get();
        Funcionario centroCustoFuncionario = funcionarioRepository.findFirstByCentro(centroCusto);
        if (centroCustoFuncionario != null) {
            return WebUtils.getMessage("centroCusto.funcionario.centroCusto.referenced", centroCustoFuncionario.getId());
        }
        return null;
    }
    
    @Override
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.CENTROCUSTO);
        Page<CentroCusto> page = datatables.getSearch().isEmpty() ? centroCustoRepository.findAll(datatables.getPageable())
                : centroCustoRepository.searchAll(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }
    
    @Override
    public CentroCusto findByNome(String nome) {
        return centroCustoRepository.findByNome(nome).get();
    }
    
}

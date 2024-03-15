package br.ind.cmil.gestao.pessoa.service.imp;

import br.ind.cmil.gestao.datatables.Datatables;
import br.ind.cmil.gestao.datatables.DatatablesColunas;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import br.ind.cmil.gestao.pessoa.repository.PessoaJuridicaRepository;
import br.ind.cmil.gestao.pessoa.service.PessoaJuridicaService;
import br.ind.cmil.gestao.pessoa.domain.PessoaJuridica;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraaocalelessocassi
 */
@Service
public class PessoaJuridicaServiceImp implements PessoaJuridicaService {

    private final PessoaJuridicaRepository empresaRepository;
    
    private final Datatables datatables;

    public PessoaJuridicaServiceImp(PessoaJuridicaRepository empresaRepository, Datatables datatables) {
        this.empresaRepository = empresaRepository;
        this.datatables = datatables;
    }

   
   
    @Override
    public PessoaJuridica save(PessoaJuridica empresa) {
        return empresaRepository.save(empresa);
    }

    @Override
    public PessoaJuridica update(PessoaJuridica empresa) {
        return empresaRepository.save(empresa);
    }

    @Override
    public PessoaJuridica buscarEmpresaPorId(Long id) {
        return empresaRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        empresaRepository.deleteById(id);
    }

    @Override
    public List<PessoaJuridica> getEmpresas() {
        return empresaRepository.findAll(Sort.by("id"));
    }
  @Transactional(readOnly = true)
    @Override
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
       datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.PESSOAJURIDICA);
        Page<PessoaJuridica> page = datatables.getSearch().isEmpty() ? empresaRepository.findAll(datatables.getPageable())
                : empresaRepository.searchAll(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

}

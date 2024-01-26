
package br.ind.cmil.gestao.assistente.service.impl;

import br.ind.cmil.gestao.assistente.domain.AssistenteAdministrativo;
import br.ind.cmil.gestao.assistente.mapper.AssistenteAdministrativoMapper;
import br.ind.cmil.gestao.assistente.repository.AssistenteAdministrativoRepository;
import br.ind.cmil.gestao.assistente.service.AssistenteAdministrativoService;
import br.ind.cmil.gestao.datatables.Datatables;
import br.ind.cmil.gestao.datatables.DatatablesColunas;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ti
 */
@Service
public class AssistenteAdministrativoServiceImp implements AssistenteAdministrativoService {

    private final AssistenteAdministrativoRepository ar;
    private final AssistenteAdministrativoMapper am;
    private final Datatables datatables;

    public AssistenteAdministrativoServiceImp(AssistenteAdministrativoRepository ar, AssistenteAdministrativoMapper am, Datatables datatables) {
        this.ar = ar;
        this.am = am;
        this.datatables = datatables;
    }

    @Override
    public AssistenteAdministrativo buscarPorUsuarioId(Long id) {
        return ar.findById(id).get();
    }

    @Override
    public void create(AssistenteAdministrativo a) {
        if (a.getId() == null) {
            ar.save(a);
        }
        update(a);
    }

    private AssistenteAdministrativo update(AssistenteAdministrativo a) {
        return ar.findById(a.getId()).get();
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> assistentes(HttpServletRequest request) {

        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.ASSISTENTEADMINISTRATIVO);
        Page<?> page = datatables.getSearch().isEmpty() ? ar.findAll(datatables.getPageable())
                : ar.searchAll(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    public void delete(Long id) {
        Optional<AssistenteAdministrativo> assisnte = ar.findById(id);
        ar.delete(assisnte.get());
    }

    @Override
    public AssistenteAdministrativo buscarPorEmail(String email) {
        return ar.findByUsuarioNomeOrEmail(email, email).orElse(new AssistenteAdministrativo());
    }

}


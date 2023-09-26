package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.model.datatables.Datatables;
import br.ind.cmil.gestao.model.datatables.DatatablesColunas;
import br.ind.cmil.gestao.model.dto.PresencaDTO;
import br.ind.cmil.gestao.model.dto.mappers.PresencaMapper;
import br.ind.cmil.gestao.model.entidades.Presenca;
import br.ind.cmil.gestao.model.repositorys.IPresencaRepository;
import br.ind.cmil.gestao.model.services.interfaces.IPresencaService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
@Service
public class PresencaServiceImp implements IPresencaService {

    private final IPresencaRepository pr;
    private final PresencaMapper pm;
    private final Datatables datatables;

    public PresencaServiceImp(IPresencaRepository pr, PresencaMapper pm, Datatables datatables) {
        this.pr = pr;
        this.pm = pm;
        this.datatables = datatables;
    }

    @Override
    public PresencaDTO findById(Long id) {
        return pr.findById(id).map(pm::toDTO).get();
    }

    @Override
    public void create(PresencaDTO p) {
        if (p.id() == null) {
            pr.save(pm.toEntity(p));
        }
        update(p);
    }

    private PresencaDTO update(PresencaDTO p) {
        return pr.findById(p.id()).map(pm::toDTO).get();
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> presencas(HttpServletRequest request) {

        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.PERFIL);
        Page<?> page = datatables.getSearch().isEmpty() ? pr.findAll(datatables.getPageable())
                : pr.searchAll(pm.convertPresencaValue(datatables.getSearch()), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    public void delete(Long id) {
        Optional<Presenca> presenca = pr.findById(id);
        pr.delete(presenca.get());
    }

}

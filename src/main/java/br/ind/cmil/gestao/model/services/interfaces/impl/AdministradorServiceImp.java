package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.model.datatables.Datatables;
import br.ind.cmil.gestao.model.datatables.DatatablesColunas;
import br.ind.cmil.gestao.model.dto.mappers.AdministradorMapper;
import br.ind.cmil.gestao.model.entidades.Administrador;
import br.ind.cmil.gestao.model.repositorys.IAdministradorRepository;
import br.ind.cmil.gestao.model.services.interfaces.IAdministradorService;
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
public class AdministradorServiceImp implements IAdministradorService {

    private final IAdministradorRepository ar;
    private final AdministradorMapper am;
    private final Datatables datatables;

    public AdministradorServiceImp(IAdministradorRepository ar, AdministradorMapper am, Datatables datatables) {
        this.ar = ar;
        this.am = am;
        this.datatables = datatables;
    }

    @Override
    @Transactional(readOnly = false)
    public void salvar(Administrador administrador) {
        if (administrador.getId() == null) {
            ar.save(administrador);
        }
        update(administrador);
    }

    private Administrador update(Administrador a) {
        return ar.findById(a.getId()).get();
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> administradores(HttpServletRequest request) {

        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.ADMINISTRADOR);
        Page<?> page = datatables.getSearch().isEmpty() ? ar.findAll(datatables.getPageable())
                : ar.searchAll(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        Optional<Administrador> administrador = ar.findById(id);
        ar.delete(administrador.get());
    }

    @Override
    @Transactional(readOnly = true)
    public Administrador buscarPorUsuarioId(Long id) {
        return ar.findById(id).orElse(new Administrador());
    }

    @Override
    @Transactional(readOnly = true)
    public Administrador buscarPorEmail(String email) {
        return ar.findByUsuarioEmail(email, email).orElse(new Administrador());
    }

}

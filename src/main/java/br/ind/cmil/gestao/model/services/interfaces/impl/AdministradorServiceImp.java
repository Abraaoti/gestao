package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.model.datatables.Datatables;
import br.ind.cmil.gestao.model.datatables.DatatablesColunas;
import br.ind.cmil.gestao.model.dto.AdministradorDTO;
import br.ind.cmil.gestao.model.dto.mappers.AdministradorMapper;
import br.ind.cmil.gestao.model.entidades.Administrador;
import br.ind.cmil.gestao.model.repositorys.IAdministradorRepository;
import br.ind.cmil.gestao.model.services.interfaces.IAdministradorService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.User;
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
    public AdministradorDTO findById(Long id) {
        return ar.findById(id).map(am::toDTO).get();
    }

    @Override
    public void create(AdministradorDTO a) {
        if (a.id() == null) {
            ar.save(am.toEntity(a));
        }
        update(a);
    }

    public AdministradorDTO update(AdministradorDTO a) {
        return ar.findById(a.id()).map(am::toDTO).get();
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> administradores(HttpServletRequest request) {

        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.PERFIL);
        Page<?> page = datatables.getSearch().isEmpty() ? ar.findAll(datatables.getPageable())
                : ar.searchAll(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    public void delete(Long id) {
        Optional<Administrador> administrador = ar.findById(id);
        ar.delete(administrador.get());
    }

    @Override
    public AdministradorDTO buscarPorUsuarioId(Long id) {
        return ar.findById(id).map(am::toDTO).get();
    }

    @Override
    public AdministradorDTO buscarPorEmail(String email) {
        return ar.findByUsuarioEmail(email, email).map(am::toDTO).get();
    }

    @Override
    public AdministradorDTO form(AdministradorDTO administrador, User user) {
        if (administrador.id() != null) {
            return ar.findByUsuarioEmail(user.getUsername(), user.getUsername()).map(am::toDTO).get();
        }
        return administrador;
    }

}

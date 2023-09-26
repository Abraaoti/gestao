package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.model.datatables.Datatables;
import br.ind.cmil.gestao.model.datatables.DatatablesColunas;
import br.ind.cmil.gestao.model.dto.AdministradorDTO;
import br.ind.cmil.gestao.model.dto.AssistenteAdministrativoDTO;
import br.ind.cmil.gestao.model.dto.mappers.AdministradorMapper;
import br.ind.cmil.gestao.model.dto.mappers.AssistenteAdministrativoMapper;
import br.ind.cmil.gestao.model.entidades.Administrador;
import br.ind.cmil.gestao.model.entidades.AssistenteAdministrativo;
import br.ind.cmil.gestao.model.repositorys.IAdministradorRepository;
import br.ind.cmil.gestao.model.repositorys.IAssistenteAdministrativoRepository;
import br.ind.cmil.gestao.model.services.interfaces.IAdministradorService;
import br.ind.cmil.gestao.model.services.interfaces.IAssistenteAdministrativoService;
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
public class AssistenteAdministrativoServiceImp implements IAssistenteAdministrativoService {

    private final IAssistenteAdministrativoRepository ar;
    private final AssistenteAdministrativoMapper am;
    private final Datatables datatables;

    public AssistenteAdministrativoServiceImp(IAssistenteAdministrativoRepository ar, AssistenteAdministrativoMapper am, Datatables datatables) {
        this.ar = ar;
        this.am = am;
        this.datatables = datatables;
    }

    

    @Override
    public AssistenteAdministrativoDTO findById(Long id) {
        return ar.findById(id).map(am::toDTO).get();
    }

    @Override
    public void create(AssistenteAdministrativoDTO a) {
        if (a.id() == null) {
            ar.save(am.toEntity(a));
        }
        update(a);
    }

    private AssistenteAdministrativoDTO update(AssistenteAdministrativoDTO a) {
        return ar.findById(a.id()).map(am::toDTO).get();
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
    public AssistenteAdministrativoDTO buscarPorUsuarioId(Long id) {
        return ar.findById(id).map(am::toDTO).get();
    }

    @Override
    public AssistenteAdministrativoDTO buscarPorEmail(String email) {
        return ar.findByUsuarioEmail(email, email).map(am::toDTO).get();
    }

    @Override
    public AssistenteAdministrativoDTO form(AssistenteAdministrativoDTO assistente, User user) {
        if (assistente.id() != null) {
            return ar.findByUsuarioEmail(user.getUsername(),user.getUsername()).map(am::toDTO).get();
        }
        return assistente;
    }

}

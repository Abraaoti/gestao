package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.dto.AuxiliarAdministrativoDTO;
import br.ind.cmil.gestao.model.datatables.Datatables;
import br.ind.cmil.gestao.model.datatables.DatatablesColunas;
import br.ind.cmil.gestao.model.dto.mappers.AuxiliarAdministrativoMapper;
import br.ind.cmil.gestao.model.entidades.AuxiliarAdministrativo;
import br.ind.cmil.gestao.model.repositorys.IAuxiliarAdministrativoRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ind.cmil.gestao.model.services.interfaces.AuxiliarAdministrativoService;

/**
 *
 * @author abraao
 */
@Service
public class AuxiliarAdministrativoServiceImp implements AuxiliarAdministrativoService {

    private final IAuxiliarAdministrativoRepository ar;
    private final AuxiliarAdministrativoMapper am;
    private final Datatables datatables;

    public AuxiliarAdministrativoServiceImp(IAuxiliarAdministrativoRepository ar, AuxiliarAdministrativoMapper am, Datatables datatables) {
        this.ar = ar;
        this.am = am;
        this.datatables = datatables;
    }

    @Transactional(readOnly = false)
    @Override
    public void create(AuxiliarAdministrativo a) {
        if (a.getId() == null) {
            ar.save(a);
        }
        update(null);
    }

    @Transactional(readOnly = false)
    private AuxiliarAdministrativoDTO update(AuxiliarAdministrativoDTO a) {
        return ar.findById(a.id()).map(am::toDTO).get();
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> aux(HttpServletRequest request) {

        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.AUXILIARADMINISTRATIVO);
        Page<?> page = datatables.getSearch().isEmpty() ? ar.findAll(datatables.getPageable())
                : ar.searchAll(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
     @Transactional(readOnly = false)
    public void delete(Long id) {
        Optional<AuxiliarAdministrativo> auxiliar = ar.findById(id);
        ar.delete(auxiliar.get());
    }

    @Override
     @Transactional(readOnly = true)
    public AuxiliarAdministrativo buscarPorUsuarioId(Long id) {
        return ar.findById(id).orElse(new AuxiliarAdministrativo());
    }

    @Override
     @Transactional(readOnly = true)
    public AuxiliarAdministrativo buscarPorEmail(String email) {
        return ar.findByUsuarioEmail(email, email).orElse(new AuxiliarAdministrativo());
    }

    @Override
     @Transactional(readOnly = true)
    public AuxiliarAdministrativo form(AuxiliarAdministrativo auxiliar, User user) {
        if (auxiliar.getId() == null) {
            auxiliar = ar.findByUsuarioEmail(user.getUsername(), user.getUsername()).orElse(new AuxiliarAdministrativo());
        }
        return auxiliar;

    }

    @Override
    @Transactional(readOnly = true)
    public Set<AuxiliarAdministrativo> list(Pageable pageable) {
        return ar.searchAll(pageable).stream().map((auxilair)-> auxilair).collect(Collectors.toSet());

    }

}

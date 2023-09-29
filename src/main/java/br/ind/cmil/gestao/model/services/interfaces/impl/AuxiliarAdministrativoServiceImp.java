package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.model.datatables.Datatables;
import br.ind.cmil.gestao.model.datatables.DatatablesColunas;
import br.ind.cmil.gestao.model.dto.AuxiliarAdministrativoDTO;
import br.ind.cmil.gestao.model.dto.mappers.AuxiliarAdministrativoMapper;
import br.ind.cmil.gestao.model.entidades.AuxiliarAdministrativo;
import br.ind.cmil.gestao.model.repositorys.IAuxiliarAdministrativoRepository;
import br.ind.cmil.gestao.model.services.interfaces.IAuxiliarAdministrativoService;
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

/**
 *
 * @author abraao
 */
@Service
public class AuxiliarAdministrativoServiceImp implements IAuxiliarAdministrativoService {

    private final IAuxiliarAdministrativoRepository ar;
    private final AuxiliarAdministrativoMapper am;
    private final Datatables datatables;

    public AuxiliarAdministrativoServiceImp(IAuxiliarAdministrativoRepository ar, AuxiliarAdministrativoMapper am, Datatables datatables) {
        this.ar = ar;
        this.am = am;
        this.datatables = datatables;
    }

    @Override
    public AuxiliarAdministrativoDTO findById(Long id) {
        return ar.findById(id).map(am::toDTO).get();
    }

    @Override
    public void create(AuxiliarAdministrativoDTO a) {
        if (a.id() == null) {
            ar.save(am.toEntity(a));
        }
        update(a);
    }

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
    public void delete(Long id) {
        Optional<AuxiliarAdministrativo> auxiliar = ar.findById(id);
        ar.delete(auxiliar.get());
    }

    @Override
    public AuxiliarAdministrativoDTO buscarPorUsuarioId(Long id) {
        return ar.findById(id).map(am::toDTO).get();
    }

    @Override
    public AuxiliarAdministrativoDTO buscarPorEmail(String email) {
        return ar.findByUsuarioEmail(email, email).map(am::toDTO).get();
    }

    @Override
    public AuxiliarAdministrativoDTO form(AuxiliarAdministrativoDTO auxiliar, User user) {
        if (auxiliar.id() != null) {
            return ar.findByUsuarioEmail(user.getUsername(), user.getUsername()).map(am::toDTO).get();
        }
        return auxiliar;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<AuxiliarAdministrativoDTO> list(Pageable pageable) {
        return ar.searchAll(pageable).stream().map(am::toDTO).collect(Collectors.toSet());

    }

}

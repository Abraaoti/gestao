package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.dto.DiretorDTO;
import br.ind.cmil.gestao.model.datatables.Datatables;
import br.ind.cmil.gestao.model.datatables.DatatablesColunas;
import br.ind.cmil.gestao.model.dto.mappers.DiretorMapper;
import br.ind.cmil.gestao.model.entidades.Diretor;
import br.ind.cmil.gestao.model.repositorys.IDiretorRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ind.cmil.gestao.model.services.interfaces.DiretorService;

/**
 *
 * @author abraao
 */
@Service
public class DiretorServiceImp implements DiretorService {

    private final IDiretorRepository ar;
    private final DiretorMapper am;
    private final Datatables datatables;

    public DiretorServiceImp(IDiretorRepository ar, DiretorMapper am, Datatables datatables) {
        this.ar = ar;
        this.am = am;
        this.datatables = datatables;
    }

    @Override
    @Transactional(readOnly = false)
    public DiretorDTO findById(Long id) {
        return ar.findById(id).map(am::toDTO).get();
    }

    @Override
    @Transactional(readOnly = false)
    public void create(DiretorDTO a) {
        if (a.id() == null) {
            ar.save(am.toEntity(a));
        }
        update(a);
    }

    private DiretorDTO update(DiretorDTO a) {
        return ar.findById(a.id()).map(am::toDTO).get();
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> diretores(HttpServletRequest request) {

        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.DIRETOR);
        Page<?> page = datatables.getSearch().isEmpty() ? ar.findAll(datatables.getPageable())
                : ar.searchAll(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        Optional<Diretor> diretor = ar.findById(id);
        ar.delete(diretor.get());
    }

    @Override
    @Transactional(readOnly = true)
    public DiretorDTO buscarPorUsuarioId(Long id) {
        return ar.findById(id).map(am::toDTO).get();
    }

    @Override
    @Transactional(readOnly = true)
    public DiretorDTO buscarPorEmail(String email) {
        return ar.findByUsuarioEmail(email, email).map(am::toDTO).get();
    }

    @Override
    @Transactional(readOnly = true)
    public DiretorDTO form(DiretorDTO diretor, User user) {
        if (diretor.id() != null) {
            return ar.findByUsuarioEmail(user.getUsername(), user.getUsername()).map(am::toDTO).get();
        }
        return diretor;
    }

}

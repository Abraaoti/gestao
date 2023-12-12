package br.ind.cmil.gestao.services.impl;

import br.ind.cmil.gestao.model.dto.DiretorDTO;
import br.ind.cmil.gestao.datatables.Datatables;
import br.ind.cmil.gestao.datatables.DatatablesColunas;
import br.ind.cmil.gestao.mappers.DiretorMapper;
import br.ind.cmil.gestao.domain.Diretor;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ind.cmil.gestao.services.DiretorService;
import br.ind.cmil.gestao.repositorys.DiretorRepository;
import java.util.Objects;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author abraao
 */
@Service
public class DiretorServiceImp implements DiretorService {

    private final DiretorRepository ar;
    private final DiretorMapper am;
    private final Datatables datatables;

    public DiretorServiceImp(DiretorRepository ar, DiretorMapper am, Datatables datatables) {
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
    public void create(DiretorDTO diretorDTO) {
        if (diretorDTO.id() == null) {
            Diretor diretor = new Diretor();
            am.toEntity(diretorDTO);
            ar.save(diretor);
        }
        update(diretorDTO);
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
    
       private void validar(DiretorDTO diretor) {
        Optional<Diretor> administrador = ar.findByNome(diretor.nome());
        if (administrador.isPresent() && !Objects.equals(administrador.get().getId(), diretor.id())) {
            throw new DataIntegrityViolationException("Diretor já cadastro no sistema!");
        }
        
    }

}

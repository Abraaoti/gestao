package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.exceptions.PerfilExistenteException;
import br.ind.cmil.gestao.model.datatables.Datatables;
import br.ind.cmil.gestao.model.datatables.DatatablesColunas;
import br.ind.cmil.gestao.model.dto.PerfilDTO;
import br.ind.cmil.gestao.model.dto.mappers.PerfilMapper;
import br.ind.cmil.gestao.model.entidades.Perfil;
import br.ind.cmil.gestao.model.repositorys.IPerfilRepository;
import br.ind.cmil.gestao.model.services.interfaces.IPerfilService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
@Service
public class PerfilServiceImp implements IPerfilService {

    private final IPerfilRepository pr;
    private final PerfilMapper pm;
    private final Datatables datatables;

    @Autowired
    public PerfilServiceImp(IPerfilRepository iPerfilRepository, PerfilMapper pm, Datatables datatables) {
        this.pr = iPerfilRepository;
        this.pm = pm;
        this.datatables = datatables;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.PERFIL);
        Page<Perfil> page = datatables.getSearch().isEmpty() ? pr.findAll(datatables.getPageable())
                : pr.findAllByPerfil(pm.convertPerfilValue(datatables.getSearch()), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<PerfilDTO> list(Pageable pageable) {
        return pr.searchAll(pageable).stream().map(pm::toDTO).collect(Collectors.toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public Set<PerfilDTO> perfis() {
        return pr.searchAll().stream().map(pm::toDTO).collect(Collectors.toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public PerfilDTO findById(Long id) {
        return pr.findById(id).map(pm::toDTO).orElseThrow(() -> new PerfilExistenteException(String.valueOf(id), "Este id não consta no bd! "));
    }

    @Transactional(readOnly = false)
    @Override
    public PerfilDTO create(PerfilDTO p) {
        if (p.id() == null) {
            validarPerfil(p);
            return pm.toDTO(pr.save(pm.toEntity(p)));
        }
        return update(p);
    }

   

    @Override
    @Transactional(readOnly = false)
    public PerfilDTO update(PerfilDTO p) {
        return pr.findById(p.id())
                .map(recordFound -> {
                    recordFound.setTp(pm.convertPerfilValue(p.p()));
                    return pm.toDTO(pr.save(recordFound));
                }).orElseThrow(() -> new PerfilExistenteException(String.valueOf(p.id()), "Este id não consta no bd! "));
    }

    @Override
    public void delete(Long id) {
        Optional<Perfil> perfil = pr.findById(id);
        pr.delete(perfil.get());
    }

    @Override
    @Transactional(readOnly = true)
    public PerfilDTO buscarPerfilPorNome(String name) {
        return pr.findByTipoPerfil(pm.convertPerfilValue(name)).map(pm::toDTO).get();
    }

    @Override
    @Transactional(readOnly = false)
    public boolean checkIfPerfilExist(String name) {
        return pr.findByTipoPerfil(pm.convertPerfilValue(name)) != null;
    }

    private void validarPerfil(PerfilDTO p) {
        Optional<Perfil> perfil = pr.findByTipoPerfil(pm.convertPerfilValue(p.p()));
        if (perfil.isPresent() && !Objects.equals(perfil.get().getId(), p.id())) {
            throw new DataIntegrityViolationException("Perfil já cadastro no sistema!");
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Set<Perfil> perfis(Set<String> roles) {
        Set<Perfil> perfis = new HashSet<>();

        if (roles.isEmpty()) {
            perfis.add(pr.findByTipoPerfil(pm.convertPerfilValue("usuário")).get());
        } else {
            for (String string : roles) {
                perfis.add(pr.findByTipoPerfil(pm.convertPerfilValue(string)).get());
            }
        }
        return perfis;

    }

    @Transactional(readOnly = false)
    public Set<Perfil> perfisVazio(Set<String> strings) {
        Set<Perfil> perfis = new HashSet<>();
        if (strings == null) {
            perfis.add(pr.findByTipoPerfil(pm.convertPerfilValue("usuário")).get());
        }

        return perfis;

    }

    private void perfis(Perfil tipo) {
        Perfil t = pr.findByTipoPerfil(tipo.getTp()).get();
        Perfil p = new Perfil();
        if (t.getTp().getValue() == null) {
            p.setTp(pm.convertPerfilValue("usuário"));
        } else {
            p.setTp(pm.convertPerfilValue(t.getTp().getValue()));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public String tipoPerfil(String tipo) {
        Perfil perfil = pr.findByTipoPerfil(pm.convertPerfilValue(tipo)).get();
        return perfil.getTp().getValue();

    }

}

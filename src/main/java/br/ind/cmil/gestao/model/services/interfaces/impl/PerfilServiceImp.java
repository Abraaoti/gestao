package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.dto.PerfilDTO;
import br.ind.cmil.gestao.exceptions.PerfilExistenteException;
import br.ind.cmil.gestao.model.datatables.Datatables;
import br.ind.cmil.gestao.model.datatables.DatatablesColunas;
import br.ind.cmil.gestao.model.dto.mappers.PerfilMapper;
import br.ind.cmil.gestao.model.entidades.Perfil;
import br.ind.cmil.gestao.model.enums.TipoPerfil;
import br.ind.cmil.gestao.model.repositorys.IPerfilRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
import br.ind.cmil.gestao.model.services.interfaces.PerfilService;

/**
 *
 * @author abraao
 */
@Service
public class PerfilServiceImp implements PerfilService {

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
                : pr.findAllByPerfil(TipoPerfil.convertPerfilValue(datatables.getSearch()), datatables.getPageable());
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
                    recordFound.setTp(TipoPerfil.convertPerfilValue(p.p()));
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
        return pr.findByTipoPerfil(TipoPerfil.convertPerfilValue(name)).map(pm::toDTO).get();
    }

    @Override
    @Transactional(readOnly = false)
    public boolean checkIfPerfilExist(String name) {
        return pr.findByTipoPerfil(TipoPerfil.convertPerfilValue(name)) != null;
    }

    private void validarPerfil(PerfilDTO p) {
        Optional<Perfil> perfil = pr.findByTipoPerfil(TipoPerfil.convertPerfilValue(p.p()));
        if (perfil.isPresent() && !Objects.equals(perfil.get().getId(), p.id())) {
            throw new DataIntegrityViolationException("Perfil já cadastro no sistema!");
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<Perfil> perfis(List<String> roles) {
        List<Perfil> perfis = new ArrayList<>();

        if (roles.isEmpty()) {
            perfis.add(pr.findByTipoPerfil(TipoPerfil.convertPerfilValue("usuário")).get());
        } else {
            for (String string : roles) {
                perfis.add(pr.findByTipoPerfil(TipoPerfil.convertPerfilValue(string)).get());
            }
        }
        return perfis;

    }

    public List<Perfil> checarPerfilAdminMaiorDois(List<String> roles) {
        List<Perfil> perfis = new ArrayList<>();

        if (roles.size() > 2
                || roles.containsAll(Arrays.asList(new Perfil("admin"), new Perfil("usuário")))
                || roles.containsAll(Arrays.asList(new Perfil("administrador"), new Perfil("usuário")))) {
           return null;
        } else {
           
        }
        return perfis;

    }

    @Override
    @Transactional(readOnly = true)
    public Boolean atualizarPerfisEUsuarios(List<String> roles) {

        if (roles.size() > 2 || roles.containsAll(Arrays.asList(new Perfil(TipoPerfil.convertPerfilValue("admin")), new Perfil(TipoPerfil.convertPerfilValue("usuário")))) || roles.containsAll(Arrays.asList(new Perfil(TipoPerfil.convertPerfilValue("administrador")), new Perfil(TipoPerfil.convertPerfilValue("usuário"))))) {
            throw new DataIntegrityViolationException("usuário não pode ser Admin e/ou administrador.");
        }
        return false;
    }

    @Transactional(readOnly = false)
    public Set<Perfil> perfisVazio(Set<String> strings) {
        Set<Perfil> perfis = new HashSet<>();
        if (strings == null) {
            perfis.add(pr.findByTipoPerfil(TipoPerfil.convertPerfilValue("usuário")).get());
        }

        return perfis;

    }

    private void perfis(Perfil tipo) {
        Perfil t = pr.findByTipoPerfil(tipo.getTp()).get();
        Perfil p = new Perfil();
        if (t.getTp().getValue() == null) {
            p.setTp(TipoPerfil.convertPerfilValue("usuário"));
        } else {
            p.setTp(TipoPerfil.convertPerfilValue(t.getTp().getValue()));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public String tipoPerfil(String tipo) {
        Perfil perfil = pr.findByTipoPerfil(TipoPerfil.convertPerfilValue(tipo)).get();
        return perfil.getTp().getValue();

    }

}

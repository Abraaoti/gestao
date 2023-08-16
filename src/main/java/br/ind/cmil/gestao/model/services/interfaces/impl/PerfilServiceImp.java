package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.exceptions.PerfilExistenteException;
import br.ind.cmil.gestao.model.dto.PerfilDTO;
import br.ind.cmil.gestao.model.dto.mappers.PerfilMapper;
import br.ind.cmil.gestao.model.entidades.Perfil;
import br.ind.cmil.gestao.model.repositorys.IPerfilRepository;
import br.ind.cmil.gestao.model.services.interfaces.IPerfilService;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
@Service
@Transactional
public class PerfilServiceImp implements IPerfilService {

    private final IPerfilRepository pr;
    private final PerfilMapper pm;

    @Autowired
    public PerfilServiceImp(IPerfilRepository iPerfilRepository, PerfilMapper pm) {
        this.pr = iPerfilRepository;
        this.pm = pm;
    }

    @Override
    public Perfil getOrCreate(String name) {
        Perfil role = pr.findByTipoPerfil(pm.convertPerfilValue(name)).get();

        if (role == null) {         
          
           role = pr.save(new Perfil(pm.convertPerfilValue("usuário")));
        }

        return role;
    }

    @Override
    public List<PerfilDTO> list() {
        return pr.findAll().stream().map(pm::toDTO).collect(Collectors.toList());
    }

    @Override
    public PerfilDTO findById(Long id) {
        return pr.findById(id).map(pm::toDTO).orElseThrow(() -> new PerfilExistenteException(String.valueOf(id), "Este id não consta no bd! "));
    }

    @Override
    public PerfilDTO create(PerfilDTO p) {
        if (p.id() == null) {
            validarPerfil(p);
            return pm.toDTO(pr.save(pm.toEntity(p)));
        }
        return update(p);
    }

    @Override
    public PerfilDTO update(PerfilDTO p) {
        return pr.findById(p.id())
                .map(recordFound -> {
                    recordFound.setTp(pm.convertPerfilValue(p.p()));
                    return pm.toDTO(pr.save(recordFound));
                }).orElseThrow(() -> new PerfilExistenteException(String.valueOf(p.id()), "Este id não consta no bd! "));
    }

    @Override
    public void delete(Long id) {
        pr.delete(pr.findById(id).orElseThrow(() -> new PerfilExistenteException(String.valueOf(id), "Este id não consta no bd! ")));
    }

    @Override
    public PerfilDTO buscarPerfilPorNome(String name) {
        return pr.findByTipoPerfil(pm.convertPerfilValue(name)).map(pm::toDTO).get();
    }

    @Override
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
    public Set<Perfil> perfis(Set<String> strings) {
        Set<Perfil> perfis = new HashSet<>();
        for (String perfisString : strings) {
            if (perfisString == null) {
                perfis.add(new Perfil(pm.convertPerfilValue("usuário")));
            }
            perfis.add(new Perfil(pm.convertPerfilValue(perfisString)));

        }
        return perfis;

    }

    public Set<PerfilDTO> perfisdto(Set<String> strings) {
        Set<PerfilDTO> perfis = new HashSet<>();
        for (String perfisString : strings) {
            Perfil p = new Perfil();
            if (perfisString == null) {
                p.setTp(pm.convertPerfilValue("usuário"));
                perfis.add(new PerfilDTO(p.getId(), p.getTp().getValue()));
            }
            p.setTp(pm.convertPerfilValue(perfisString));
            perfis.add(new PerfilDTO(p.getId(), p.getTp().getValue()));

        }
        return perfis;

    }

    public void perfis(Perfil tipo) {
        Perfil t = pr.findByTipoPerfil(tipo.getTp()).get();
        Perfil p = new Perfil();
        if (t.getTp().getValue() == null) {
            p.setTp(pm.convertPerfilValue("usuário"));
        } else {
            p.setTp(pm.convertPerfilValue(t.getTp().getValue()));
        }
    }

}

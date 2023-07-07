package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.exceptions.PerfilExistenteException;
import br.ind.cmil.gestao.model.dto.PerfilDTO;
import br.ind.cmil.gestao.model.dto.mappers.PerfilMapper;
import br.ind.cmil.gestao.model.entidades.Perfil;
import br.ind.cmil.gestao.model.enums.TipoPerfil;
import br.ind.cmil.gestao.model.repositorys.IPerfilRepository;
import br.ind.cmil.gestao.model.services.interfaces.IPerfilService;
import java.util.HashSet;
import java.util.List;
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
    public List<PerfilDTO> list() {
        return pr.findAll().stream().map(pm::toDTO).collect(Collectors.toList());
    }

    @Override
    public PerfilDTO findById(Long id) {
        return pr.findById(id).map(pm::toDTO).orElseThrow(() -> new PerfilExistenteException(String.valueOf(id), "Este id não consta no bd! "));
    }

    @Override
    public PerfilDTO create(PerfilDTO p) {
        // Perfil perfil = pr.findByTipoPerfil(pm.convertPerfilValue(p.p())).get();        

        return pm.toDTO(pr.save(pm.toEntity(p)));
    }

    @Override
    public PerfilDTO update(Long id, PerfilDTO p) {
        return pr.findById(id)
                .map(recordFound -> {
                    recordFound.setTp(pm.convertPerfilValue(p.p()));
                    return pm.toDTO(pr.save(recordFound));
                }).orElseThrow(() -> new PerfilExistenteException(String.valueOf(id), "Este id não consta no bd! "));
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
        return pr.findByTipoPerfil(pm.convertPerfilValue(name)) != null ? true : false;
    }

    private void validarPerfil(PerfilDTO p) {
        Optional<Perfil> perfil = pr.findByTipoPerfil(pm.convertPerfilValue(p.p()));
        if (perfil.isPresent() && perfil.get().getId() != p.id()) {
            throw new DataIntegrityViolationException("Perfil já cadastro no sistema!");
        }

    }

    @Override
    public Set<Perfil> perfis(Set<String> perfis) {
        Set<Perfil> roles = new HashSet<>();
        Perfil p = new Perfil();
        if (perfis == null) {
            TipoPerfil tp = pm.convertPerfilValue("usuário");
            p.setTp(tp);
            p = pr.save(p);
            roles.add(p);
        } else {
            for (String strRole : perfis) {

                p.setTp(pm.convertPerfilValue(strRole));

                roles.add(p);
            }
        }
        return roles;
    }

}

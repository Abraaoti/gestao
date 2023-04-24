package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.exception.RecordNotFoundException;
import br.ind.cmil.gestao.model.dto.PerfilDTO;
import br.ind.cmil.gestao.model.dto.mappers.PerfilMapper;
import br.ind.cmil.gestao.model.repositorys.IPerfilRepository;
import br.ind.cmil.gestao.model.services.interfaces.IPerfilService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
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
        return pr.findById(id).map(pm::toDTO).orElseThrow(() -> new RecordNotFoundException(id));
    }

    @Override
    public PerfilDTO create(PerfilDTO p) {
        return pm.toDTO(pr.save(pm.toEntity(p)));
    }

    @Override
    public PerfilDTO update(Long id, PerfilDTO p) {
        return pr.findById(id)
                .map(recordFound -> {
                    recordFound.setTp(pm.convertPerfilValue(p.p()));
                    recordFound.setId(p.id());
                    return pm.toDTO(pr.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    @Override
    public void delete(Long id) {
        pr.delete(pr.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }

}

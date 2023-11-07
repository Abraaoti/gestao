package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.dto.ProjetoDTO;
import br.ind.cmil.gestao.exceptions.PerfilExistenteException;
import br.ind.cmil.gestao.model.datatables.Datatables;
import br.ind.cmil.gestao.model.datatables.DatatablesColunas;
import br.ind.cmil.gestao.model.dto.mappers.ProjetoMapper;
import br.ind.cmil.gestao.model.entidades.Projeto;
import br.ind.cmil.gestao.model.repositorys.IProjetoRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ind.cmil.gestao.model.services.interfaces.ProjetoService;

/**
 *
 * @author abraao
 */
@Service
@Transactional
public class ProjetoServiceImp implements ProjetoService {

    private final IProjetoRepository pr;
    private final ProjetoMapper pm;
    private final Datatables datatables;

    public ProjetoServiceImp(IProjetoRepository pr, ProjetoMapper pm, Datatables datatables) {
        this.pr = pr;
        this.pm = pm;
        this.datatables = datatables;
    }

  
    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.PROJETO);
        Page<?> page = datatables.getSearch().isEmpty() ? pr.findAll(datatables.getPageable())
                : pr.searchAll(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

 

    @Override
    @Transactional(readOnly = true)
    public Set<ProjetoDTO> perfis() {
        return pr.searchAll().stream().map(pm::toDTO).collect(Collectors.toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public ProjetoDTO findById(Long id) {
        return pr.findById(id).map(pm::toDTO).orElseThrow(() -> new PerfilExistenteException(String.valueOf(id), "Este id não consta no bd! "));
    }

    @Override
    public ProjetoDTO create(ProjetoDTO p) {
        if (p.id() == null) {
            validarPerfil(p);
            return pm.toDTO(pr.save(pm.toEntity(p)));
        }
        return update(p);
    }

   
    @Transactional(readOnly = false)
    protected ProjetoDTO update(ProjetoDTO p) {
        return pr.findById(p.id()).map(pm::toDTO).get();
               
    }

    @Override
    public void delete(Long id) {
        Optional<Projeto> projeto = pr.findById(id);
        pr.delete(projeto.get());
    }

   

    private void validarPerfil(ProjetoDTO p) {
        Optional<Projeto> projeto = pr.findByContrato(p.contrato());
        if (projeto.isPresent() && !Objects.equals(projeto.get().getId(), p.id())) {
            throw new DataIntegrityViolationException("Perfil já cadastro no sistema!");
        }

    }


}

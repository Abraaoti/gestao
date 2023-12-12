package br.ind.cmil.gestao.services.impl;

import br.ind.cmil.gestao.datatables.Datatables;
import br.ind.cmil.gestao.datatables.DatatablesColunas;
import br.ind.cmil.gestao.domain.Presenca;
import br.ind.cmil.gestao.mappers.PresencaMapper;
import br.ind.cmil.gestao.model.dto.PresencaDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ind.cmil.gestao.repositorys.PresencaRepository;
import br.ind.cmil.gestao.services.PresencaService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 *
 * @author abraao
 */
@Service
public class PresencaServiceImp implements PresencaService {

    private final PresencaRepository presencaRepository;
   // private final PresencaMapper presencaMapper;
    private final Datatables datatables;

    public PresencaServiceImp(PresencaRepository presencaRepository, Datatables datatables) {
        this.presencaRepository = presencaRepository;
        this.datatables = datatables;
    }

   

    @Override
    @Transactional(readOnly = true)
    public List<Presenca> getPresencas() {

        List<Presenca> funcionarios = presencaRepository.findAll(Sort.by("id"));
       // return funcionarios.stream().map(presenca -> new Presenca()).collect(Collectors.toList());
        return funcionarios;

    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Presenca salvar(Presenca presencaDTO) {


        return presencaRepository.save(presencaDTO);

    }

    @Override
    public Presenca update(Long id, Presenca presenca) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PresencaDTO findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Map<String, Object> frequencias(HttpServletRequest request) {
         datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.PRESENCA);
        Page<Presenca> page = datatables.getSearch().isEmpty() ? presencaRepository.findAll(datatables.getPageable())
                : presencaRepository.searchAll(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  

}

package br.ind.cmil.gestao.services.impl;

import br.ind.cmil.gestao.datatables.Datatables;
import br.ind.cmil.gestao.datatables.DatatablesColunas;
import br.ind.cmil.gestao.domain.Frequencia;
import br.ind.cmil.gestao.domain.Funcionario;
import br.ind.cmil.gestao.enums.TipoFrequencia;
import br.ind.cmil.gestao.mappers.FrequenciaMapper;
import br.ind.cmil.gestao.model.dto.FrequenciaDTO;
import br.ind.cmil.gestao.repositorys.FrequenciaRepository;
import br.ind.cmil.gestao.repositorys.FuncionarioRepository;
import br.ind.cmil.gestao.services.FrequenciaService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 *
 * @author abraao
 */
@Service
public class FrequenciaServiceImp implements FrequenciaService {

    private final FrequenciaRepository frequenciaRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final FrequenciaMapper frequenciaMapper;
    private final Datatables datatables;

    public FrequenciaServiceImp(FrequenciaRepository frequenciaRepository, FuncionarioRepository funcionarioRepository, FrequenciaMapper frequenciaMapper, Datatables datatables) {
        this.frequenciaRepository = frequenciaRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.frequenciaMapper = frequenciaMapper;
        this.datatables = datatables;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Frequencia> getFrequencias() {

        List<Frequencia> frequencias = frequenciaRepository.findAll(Sort.by("id"));       
        return frequencias;

    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Long salvar(FrequenciaDTO frequenciaDTO) {

        Frequencia frequencia = frequenciaMapper.toEntity(frequenciaDTO);

        if (frequenciaDTO.id() == null) {
            List<Funcionario> funcionarios = funcionarioRepository.findAllById(frequenciaDTO.funcionarios());

            funcionarios.forEach(funcionario -> {
                funcionario.addFrequencia(frequencia);
            });

            return frequenciaRepository.save(frequencia).getId();
        }

        return update(frequenciaDTO).id();

    }

    private FrequenciaDTO update(FrequenciaDTO frequenciaDTO) {
        Optional<Frequencia> upFrequencia = frequenciaRepository.findById(frequenciaDTO.id());

        if (upFrequencia.isEmpty()) {
            return null;
        }
        Frequencia frequencia = upFrequencia.get();
        frequencia.setData(frequenciaDTO.data());
        frequencia.setStatus(TipoFrequencia.convertTipoTipoFrequencia(frequenciaDTO.status()));

        List<Funcionario> funcionarios = funcionarioRepository.findAllById(frequenciaDTO.funcionarios());

        funcionarios.forEach(funcionario -> {
            funcionario.addFrequencia(frequencia);
        });
        //Funcionario funcionario = funcionarioRepository.findById(frequenciaDTO.funcionario()).get();
        // frequencia.setFuncionario(funcionario);
        frequencia.setId(frequenciaDTO.id());
        return frequenciaMapper.toDTO(frequenciaRepository.save(frequencia));
    }

    @Override
    public FrequenciaDTO findById(Long id) {
        return frequenciaRepository.findById(id).map(frequencia -> frequenciaMapper.toDTO(frequencia)).get();
    }

    @Override
    public Map<String, Object> frequencias(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.FREQUENCIA);
        Page<Frequencia> page = datatables.getSearch().isEmpty() ? frequenciaRepository.findAll(datatables.getPageable())
                : frequenciaRepository.searchAll(TipoFrequencia.convertTipoTipoFrequencia(datatables.getSearch()), datatables.getPageable());
        
        return datatables.getResponse(page);
    }

    @Override
    public List<Frequencia> f() {
        Page<Frequencia> page = frequenciaRepository.findAll(PageRequest.of(0, 5));
        List<Frequencia> frequencias = frequenciaRepository.findAllFrequencias(page.stream().collect(Collectors.toList()));
        return frequencias;
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public FrequenciaDTO criar(List<Long> funcionario_ids, FrequenciaDTO frequencia) {
        //List<Funcionario> funcionarios = funcionarioRepository.findAllById(funcionario_ids);
        Set<Long> funcionarioIds = funcionarioRepository.findAllById(funcionario_ids).stream().map(funcionarios_id -> funcionarios_id.getId()).collect(Collectors.toSet());
        return new FrequenciaDTO(frequencia.id(), frequencia.data(), frequencia.status(), funcionarioIds);
    }

    @Override
    public Map<String, Object> funcionariosFrequencias(HttpServletRequest request) {
   datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.FUNCIONARIO_FREQUENCIA);
        Page<Frequencia> page = datatables.getSearch().isEmpty() ? frequenciaRepository.findAll(datatables.getPageable())
                : frequenciaRepository.searchAll(TipoFrequencia.convertTipoTipoFrequencia(datatables.getSearch()), datatables.getPageable());
        
        return datatables.getResponse(page);
    }

}

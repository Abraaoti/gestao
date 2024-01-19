package br.ind.cmil.gestao.services.impl;

import br.ind.cmil.gestao.datatables.Datatables;
import br.ind.cmil.gestao.datatables.DatatablesColunas;
import br.ind.cmil.gestao.domain.Frequencia;
import br.ind.cmil.gestao.domain.Funcionario;
import br.ind.cmil.gestao.enums.TipoFrequencia;
import br.ind.cmil.gestao.mappers.FrequenciaMapper;
import br.ind.cmil.gestao.model.dto.FrequenciaDTO;
import br.ind.cmil.gestao.model.dto.FuncionarioDTO;
import br.ind.cmil.gestao.repositorys.FrequenciaRepository;
import br.ind.cmil.gestao.repositorys.FuncionarioRepository;
import br.ind.cmil.gestao.services.FrequenciaService;
import br.ind.cmil.gestao.services.FuncionarioService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 *
 * @author abraao
 */
@Service
public class FrequenciaServiceImp implements FrequenciaService {

    private final FrequenciaRepository frequenciaRepository;
    private final FuncionarioService funcionarioService;
    private final FrequenciaMapper frequenciaMapper;
    private final Datatables datatables;

    public FrequenciaServiceImp(FrequenciaRepository frequenciaRepository, FuncionarioService funcionarioService, FrequenciaMapper frequenciaMapper, Datatables datatables) {
        this.frequenciaRepository = frequenciaRepository;
        this.funcionarioService = funcionarioService;
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
            Set<Funcionario> funcionarios = funcionarioService.funcionarioString(frequenciaDTO.funcionarios());

           frequencia.setFuncionarios(funcionarios);

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

        //  List<Funcionario> funcionarios = funcionarioRepository.findAllById(frequenciaDTO.funcionarios());
        frequenciaDTO.funcionarios().forEach(Funcionario::new);

        frequencia.setId(frequenciaDTO.id());
        return frequenciaMapper.toDTO(frequenciaRepository.save(frequencia));
    }

    @Override
    @Transactional(readOnly = true)
    public FrequenciaDTO findById(Long id) {
        return frequenciaRepository.findById(id).map(frequencia -> frequenciaMapper.toDTO(frequencia)).get();
    }

    @Override
    public void delete(Long id) {
        frequenciaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, Object> funcionariosFrequencias(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.FREQUENCIA);
        Page<Frequencia> page = datatables.getSearch().isEmpty() ? frequenciaRepository.findAll(datatables.getPageable())
                : frequenciaRepository.searchAll(TipoFrequencia.convertTipoTipoFrequencia(datatables.getSearch()), datatables.getPageable());

        return datatables.getResponse(page);
    }

    @Transactional(readOnly = true)
    @Override
    public FrequenciaDTO form(Long funcionarioId, FrequenciaDTO frequenciaDTO) {

        FuncionarioDTO funcionario = funcionarioService.buscarFuncionarioPorId(funcionarioId);
        Set<String> funcionarios = new HashSet<>();
        funcionarios.add(funcionario.nome());

        return new FrequenciaDTO(frequenciaDTO.id(), frequenciaDTO.data(), frequenciaDTO.status(), funcionarios);
    }

}

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

/**
 *
 * @author abraao
 */
//@Service
public class FrequenciaServiceImp1{/** implements FrequenciaService {

    private final FrequenciaRepository frequenciaRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final FrequenciaMapper frequenciaMapper;
    private final Datatables datatables;

    public FrequenciaServiceImp1(FrequenciaRepository frequenciaRepository, FuncionarioRepository funcionarioRepository, FrequenciaMapper frequenciaMapper, Datatables datatables) {
        this.frequenciaRepository = frequenciaRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.frequenciaMapper = frequenciaMapper;
        this.datatables = datatables;
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
  @Transactional(readOnly = false)
    private FrequenciaDTO update(FrequenciaDTO frequenciaDTO) {
        Optional<Frequencia> upFrequencia = frequenciaRepository.findById(frequenciaDTO.id());

        if (upFrequencia.isEmpty()) {
            return null;
        }
        Frequencia frequencia = upFrequencia.get();
        //frequencia.setData(frequenciaDTO.data());
        //frequencia.setStatus(TipoFrequencia.convertTipoTipoFrequencia(frequenciaDTO.status()));

        List<Funcionario> funcionarios = funcionarioRepository.findAllById(frequenciaDTO.funcionarios());

        funcionarios.forEach(funcionario -> {
            funcionario.addFrequencia(frequencia);
        });
        //Funcionario funcionario = funcionarioRepository.findById(frequenciaDTO.funcionario()).get();
        // frequencia.setFuncionario(funcionario);
        frequencia.setId(frequenciaDTO.id());
        return frequenciaMapper.toDTO(frequenciaRepository.save(frequencia));
    }

    @Transactional(readOnly = true)
    @Override
    public FrequenciaDTO findById(Long id) {
        return frequenciaRepository.findById(id).map(frequencia -> frequenciaMapper.toDTO(frequencia)).get();
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, Object> buscarFrequencias(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.FREQUENCIA);
        Page<?> page = datatables.getSearch().isEmpty() ? frequenciaRepository.findAll(datatables.getPageable())
                : frequenciaRepository.searchAll(TipoFrequencia.convertTipoTipoFrequencia(datatables.getSearch()), datatables.getPageable());

        return datatables.getResponse(page);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Long id) {
        frequenciaRepository.deleteById(id);
    }

    @Override
    public FrequenciaDTO criar(List<Long> funcionario_ids, FrequenciaDTO frequencia) {
        //List<Funcionario> funcionarios = funcionarioRepository.findAllById(funcionario_ids);
        Set<Long> funcionarioIds = funcionarioRepository.findAllById(funcionario_ids).stream().map(funcionarios_id -> funcionarios_id.getId()).collect(Collectors.toSet());
        return null;//new FrequenciaDTO(frequencia.id(), frequencia.data(), frequencia.status(), funcionarioIds);
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, Object> buscarFrequenciasPorFuncionario(Long id, HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.FUNCIONARIO_FREQUENCIA);
        Page<Frequencia> page = frequenciaRepository.findByIdFuncionario(id, datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Frequencia> buscarPorTitulos(String[] titulos) {
        return frequenciaRepository.findByStatus(titulos);
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> buscarFrequenciaPorTermo(String termo) {
        return frequenciaRepository.findFrequenciasByTermo(termo);
    }
*/
}

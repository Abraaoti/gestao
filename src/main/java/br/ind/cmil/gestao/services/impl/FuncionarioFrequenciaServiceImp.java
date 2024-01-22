package br.ind.cmil.gestao.services.impl;

import br.ind.cmil.gestao.datatables.Datatables;
import br.ind.cmil.gestao.datatables.DatatablesColunas;
import br.ind.cmil.gestao.domain.Frequencia;
import br.ind.cmil.gestao.domain.Funcionario;
import br.ind.cmil.gestao.domain.FuncionarioFrequencia;
import br.ind.cmil.gestao.mappers.FuncionarioFrequenciaMapper;
import br.ind.cmil.gestao.model.dto.FrequenciaDTO;
import br.ind.cmil.gestao.model.dto.FuncionarioDTO;
import br.ind.cmil.gestao.model.dto.FuncionarioFrequenciaDTO;
import br.ind.cmil.gestao.repositorys.FuncionarioFrequenciaRepository;
import br.ind.cmil.gestao.services.FrequenciaService;
import br.ind.cmil.gestao.services.FuncionarioFrequenciaService;
import br.ind.cmil.gestao.services.FuncionarioService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 *
 * @author abraao
 */
@Service
public class FuncionarioFrequenciaServiceImp implements FuncionarioFrequenciaService {

    private final FuncionarioFrequenciaRepository funcionarioFrequenciaRepository;
    private final FuncionarioFrequenciaMapper funcionarioFrequenciaMapper;
    private final FuncionarioService funcionarioService;
    private final FrequenciaService frequenciaService;
    private final Datatables datatables;

    public FuncionarioFrequenciaServiceImp(FuncionarioFrequenciaRepository funcionarioFrequenciaRepository, FuncionarioFrequenciaMapper funcionarioFrequenciaMapper, FuncionarioService funcionarioService, FrequenciaService frequenciaService, Datatables datatables) {
        this.funcionarioFrequenciaRepository = funcionarioFrequenciaRepository;
        this.funcionarioFrequenciaMapper = funcionarioFrequenciaMapper;
        this.funcionarioService = funcionarioService;
        this.frequenciaService = frequenciaService;
        this.datatables = datatables;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FuncionarioFrequencia> getFuncionarioFrequencias() {

        List<FuncionarioFrequencia> frequencias = funcionarioFrequenciaRepository.findAll(Sort.by("id"));
        return frequencias;

    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Long salvar(FuncionarioFrequenciaDTO funcionarioFrequenciaDTO) {

        FuncionarioFrequencia funcionarioFrequencia = funcionarioFrequenciaMapper.toEntity(funcionarioFrequenciaDTO);

        if (funcionarioFrequenciaDTO.id() == null) {
            FuncionarioDTO funcionario = funcionarioService.buscarFuncionarioPorNome(funcionarioFrequenciaDTO.funcionario());
            funcionarioFrequencia.setFuncionario(new Funcionario(funcionario.id()));
            FrequenciaDTO frequencia = frequenciaService.buscarFrequenciaPorTipo(funcionarioFrequenciaDTO.frequencia().toLowerCase());
            funcionarioFrequencia.setFrequencia(new Frequencia(frequencia.id()));
            return  funcionarioFrequenciaRepository.save(funcionarioFrequencia).getId();
        }

        return update(funcionarioFrequenciaDTO).id();

    }

    private FuncionarioFrequenciaDTO update(FuncionarioFrequenciaDTO funcionarioFrequenciaDTO) {
        Optional<FuncionarioFrequencia> upFrequencia = funcionarioFrequenciaRepository.findById(funcionarioFrequenciaDTO.id());

        if (upFrequencia.isEmpty()) {
            return null;
        }
        FuncionarioFrequencia funcionarioFrequencia = upFrequencia.get();
        funcionarioFrequencia.setData(funcionarioFrequencia.getData());
        FuncionarioDTO funcionario = funcionarioService.buscarFuncionarioPorNome(funcionarioFrequenciaDTO.funcionario());
        funcionarioFrequencia.setFuncionario(new Funcionario(funcionario.id()));
        FrequenciaDTO frequencia = frequenciaService.buscarFrequenciaPorTipo(funcionarioFrequenciaDTO.frequencia());
        funcionarioFrequencia.setFrequencia(new Frequencia(frequencia.id()));

        funcionarioFrequencia.setId(funcionarioFrequenciaDTO.id());
        return funcionarioFrequenciaMapper.toDTO(funcionarioFrequenciaRepository.save(funcionarioFrequencia));
    }

    @Override
    @Transactional(readOnly = true)
    public FuncionarioFrequenciaDTO buscarPorId(Long id) {
        return funcionarioFrequenciaRepository.findById(id).map(frequencia -> funcionarioFrequenciaMapper.toDTO(frequencia)).get();
    }

    @Override
    public void delete(Long id) {
        funcionarioFrequenciaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, Object> buscarFuncionarioFrequencias(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.FREQUENCIA);
        Page<FuncionarioFrequencia> page = datatables.getSearch().isEmpty() ? funcionarioFrequenciaRepository.findAll(datatables.getPageable())
                : funcionarioFrequenciaRepository.findByFuncionarioAndFrequencia(datatables.getSearch(), datatables.getPageable());

        return datatables.getResponse(page);
    }

    @Transactional(readOnly = true)
    @Override
    public FuncionarioFrequenciaDTO form(Long funcionarioId, Long frequenciaId, FuncionarioFrequenciaDTO funcionarioFrequenciaDTO) {

        FuncionarioDTO funcionario = funcionarioService.buscarFuncionarioPorId(funcionarioId);
        FrequenciaDTO frequencia = frequenciaService.buscarPorId(frequenciaId);

        return new FuncionarioFrequenciaDTO(funcionarioFrequenciaDTO.id(), frequencia.status(), funcionario.nome());
    }

}

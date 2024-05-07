package br.ind.cmil.gestao.frequencia.service.imp;

import br.ind.cmil.gestao.datatables.Datatables;
import br.ind.cmil.gestao.datatables.DatatablesColunas;
import br.ind.cmil.gestao.frequencia.domain.Frequencia;
import br.ind.cmil.gestao.frequencia.mappers.FrequenciaMapper;
import br.ind.cmil.gestao.frequencia.model.FrequenciaDTO;
import br.ind.cmil.gestao.frequencia.repository.FrequenciaRepository;
import br.ind.cmil.gestao.frequencia.service.FrequenciaService;
import br.ind.cmil.gestao.funcionario.repository.FuncionarioRepository;
import br.ind.cmil.gestao.util.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraaocalelessocassi
 */
@Service
public class FrequenciaServiceImp implements FrequenciaService {

    private final FrequenciaRepository frequenciaRepository;
    private final FrequenciaMapper frequenciaMapper;
    private final FuncionarioRepository funcionarioRepository;
    private final Datatables datatables;

    public FrequenciaServiceImp(FrequenciaRepository frequenciaRepository, FrequenciaMapper frequenciaMapper, FuncionarioRepository funcionarioRepository, Datatables datatables) {
        this.frequenciaRepository = frequenciaRepository;
        this.frequenciaMapper = frequenciaMapper;
        this.funcionarioRepository = funcionarioRepository;
        this.datatables = datatables;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void salvar(FrequenciaDTO frequenciaDTO) {

        final Frequencia frequencia = frequenciaMapper.toEntity(frequenciaDTO);
        if (frequencia.getId() != null) {
            update(frequenciaDTO);
        }
        validarAtributos(frequencia);
        frequenciaRepository.save(frequencia).getId();

    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    private void update(final FrequenciaDTO frequenciaDTO) {
        final Frequencia frequencia = frequenciaRepository.findById(frequenciaDTO.id())
                .orElseThrow(NotFoundException::new);
        // mapToEntity(frequenciaDTO, frequencia);
        frequenciaRepository.save(frequencia);
    }

    @Override
    @Transactional(readOnly = true)
    public FrequenciaDTO buscarPorId(Long id) {
        Frequencia frequencia = frequenciaRepository.findById(id).get();
        return frequenciaMapper.toDTO(frequencia);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FrequenciaDTO> getFrequencias() {
        final List<Frequencia> frequencias = frequenciaRepository.findAll(Sort.by("id"));
        return frequencias.stream()
                .map(frequencia -> frequenciaMapper.toDTO(frequencia))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> buscarFrequencias(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.FREQUENCIA);

        Page<Frequencia> page = datatables.getSearch().isEmpty() ? frequenciaRepository.findAll(datatables.getPageable())
                : frequenciaRepository.searchAll(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    public void delete(Long id) {
        frequenciaRepository.delete(frequenciaRepository.findById(id).orElseThrow(() -> new NotFoundException("Este id não consta no bd! ")));

    }

    private void validarAtributos(Frequencia request) {
        Optional<Frequencia> frequencia = frequenciaRepository.findByEntrada(request.getEntrada());
        if (frequencia.isPresent() && !Objects.equals(frequencia.get().getId(), request.getId()) && !Objects.equals(frequencia.get().getFuncionario().getId(), request.getFuncionario().getId())) {
            throw new DataIntegrityViolationException("já bateu a entrada!");
        }
        frequencia = frequenciaRepository.findByIntervalo(request.getIntervalo());
        if (frequencia.isPresent() && !Objects.equals(frequencia.get().getFuncionario().getId(), request.getFuncionario().getId())) {
            throw new DataIntegrityViolationException("já bateu a saída manhã!");
        }
        frequencia = frequenciaRepository.findByRetorno(request.getRetorno());
        if (frequencia.isPresent() && !Objects.equals(frequencia.get().getFuncionario().getId(), request.getFuncionario().getId())) {
            throw new DataIntegrityViolationException("já bateu a entrada tarde!");
        }
        frequencia = frequenciaRepository.findBySaida(request.getSaida());
        if (frequencia.isPresent() && !Objects.equals(frequencia.get().getFuncionario().getId(), request.getFuncionario().getId())) {
            throw new DataIntegrityViolationException("já bateu a saída tarde!");
        }
        frequencia = frequenciaRepository.findFirstByFuncionario(request.getFuncionario());
        if (frequencia.isPresent() && !Objects.equals(frequencia.get().getFuncionario().getId(), request.getFuncionario().getId())) {
            throw new DataIntegrityViolationException("Frequência já cadastrado no sistema!");
        }
    }

    protected void ponto(Frequencia frequencia, LocalTime horaAtual) {
        if (frequencia.getIntervalo() == null || frequencia.getIntervalo().equals("")) {
            frequenciaRepository.updateIntervalo(horaAtual, frequencia.getId());
            // return frequencia.getId();
        } else if (frequencia.getRetorno() == null || frequencia.getRetorno().equals("")) {
            frequenciaRepository.updateRetorno(horaAtual, frequencia.getId());
            //return frequencia.getId();
        } else if (frequencia.getSaida() == null || frequencia.getSaida().equals("")) {
            frequenciaRepository.updateSaida(horaAtual, frequencia.getId());
            //return frequencia.getId();
        }
    }

    protected Frequencia buscarFrequenciaPorFuncionarioId(Long funcionarioId) {
        return frequenciaRepository.findByFuncionarioId(funcionarioId).orElse(new Frequencia());
    }

    @Transactional(readOnly = false)
    @Override
    public void baterPonto(Long funcionarioId, LocalTime horaAtual) {
        verificarFrequencia(funcionarioId);
    }

    protected void verificarFrequencia(Long funcionarioId) {
        LocalTime horaAtual = LocalTime.now();
        Frequencia frequencia = buscarFrequenciaPorFuncionarioId(funcionarioId);       
       
        if (frequencia.getEntrada() == null || frequencia.getEntrada().equals("")) {
            frequenciaRepository.updateEntrada(horaAtual, frequencia.getId());
            // return frequencia.getId();
        } else if (frequencia.getIntervalo() == null || frequencia.getIntervalo().equals("")) {
            frequenciaRepository.updateIntervalo(horaAtual, frequencia.getId());
            // return frequencia.getId();
        } else if (frequencia.getRetorno() == null || frequencia.getRetorno().equals("")) {
            frequenciaRepository.updateRetorno(horaAtual, frequencia.getId());
            //return frequencia.getId();
        } else if (frequencia.getSaida() == null || frequencia.getSaida().equals("")) {
            frequenciaRepository.updateSaida(horaAtual, frequencia.getId());
            //return frequencia.getId();
        }
    }

}

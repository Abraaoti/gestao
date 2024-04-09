package br.ind.cmil.gestao.frequencia.service.imp;

import br.ind.cmil.gestao.datatables.Datatables;
import br.ind.cmil.gestao.datatables.DatatablesColunas;
import br.ind.cmil.gestao.enums.TipoFrequencia;
import br.ind.cmil.gestao.frequencia.domain.Frequencia;
import br.ind.cmil.gestao.frequencia.mappers.FrequenciaMapper;
import br.ind.cmil.gestao.frequencia.model.FrequenciaDTO;
import br.ind.cmil.gestao.frequencia.repository.FrequenciaRepository;
import br.ind.cmil.gestao.frequencia.service.FrequenciaService;
import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import br.ind.cmil.gestao.funcionario.repository.FuncionarioRepository;
import br.ind.cmil.gestao.util.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
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
    public Long salvar(FrequenciaDTO frequenciaDTO) {

        final Frequencia frequencia = frequenciaMapper.toEntity(frequenciaDTO);
        Funcionario funcionario = funcionarioRepository.findById(frequenciaDTO.funcionario()).get();
        frequencia.setData(LocalDate.now());
        if (frequenciaDTO.status().equalsIgnoreCase("presente")) {
            frequencia.setEntradaManha(LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond(), LocalTime.now().getNano()));

        } else {
            frequencia.setEntradaManha(null);
            frequencia.setSaidaManha(null);
            frequencia.setEntradaTarde(null);
            frequencia.setSaidaTarde(null);
            frequencia.setStatus(TipoFrequencia.convertTipoTipoFrequencia("falta"));
        }

        frequencia.setFuncionario(funcionario);
        return frequenciaRepository.save(frequencia).getId();
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void update(final Long id, final FrequenciaDTO frequenciaDTO) {
        final Frequencia frequencia = frequenciaRepository.findById(id)
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
    public FrequenciaDTO buscarFrequenciaPorTipo(String tipo) {
        Frequencia frequencia = frequenciaRepository.findByStatus(TipoFrequencia.convertTipoTipoFrequencia(tipo)).get();
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

        Page<?> page = datatables.getSearch().isEmpty() ? frequenciaRepository.findAll(datatables.getPageable())
                : frequenciaRepository.searchAll(TipoFrequencia.convertTipoTipoFrequencia(datatables.getSearch()), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    public void delete(Long id) {
        final Frequencia frequencia = frequenciaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        //funcionarioRepository.findAllByFrequencias(frequencia)
        // .forEach(funcionario -> funcionario.getFrequencias().remove(frequencia));
        frequenciaRepository.delete(frequencia);
    }

    protected void saidaManha(Long id) {
        final Frequencia frequencia = frequenciaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        if (frequencia.getData() != null && frequencia.getEntradaManha() != null && frequencia.getId().equals(id) && frequencia.getSaidaManha() == null) {
            frequencia.setSaidaManha(LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond(), LocalTime.now().getNano()));
            frequenciaRepository.save(frequencia);
        }
    }

    protected void entradaTarde(Long id) {
        final Frequencia frequencia = frequenciaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        if (frequencia.getData() != null && frequencia.getEntradaManha() != null && frequencia.getId().equals(id) && frequencia.getEntradaTarde() == null) {
            frequencia.setEntradaTarde(LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond(), LocalTime.now().getNano()));
            frequenciaRepository.save(frequencia);
        }

    }

    protected void saidaTarde(Long id) {
        final Frequencia frequencia = frequenciaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        if (frequencia.getData() != null && frequencia.getEntradaManha() != null && frequencia.getId().equals(id) && frequencia.getSaidaTarde() == null) {
            frequencia.setSaidaTarde(LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond(), LocalTime.now().getNano()));
            frequenciaRepository.save(frequencia);
        }

    }

}

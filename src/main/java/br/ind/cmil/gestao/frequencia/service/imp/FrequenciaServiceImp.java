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
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

        final Frequencia frequencia = new Frequencia();
        Funcionario funcionario = funcionarioRepository.findById(frequenciaDTO.funcionario()).get();
        Optional<Frequencia> freq = frequenciaRepository.findFirstByFuncionario(funcionario);
        if (freq.isPresent()) {
            this.ponto(freq.get(), frequenciaDTO);
            frequencia.setStatus(TipoFrequencia.convertTipoTipoFrequencia("falta"));
            frequencia.setEntrada(null);
            frequencia.setIntervalo(null);
            frequencia.setRetorno(null);
            frequencia.setSaida(null);
            frequencia.getId();
        }
        frequencia.setFuncionario(funcionario);
        frequencia.setData(LocalDate.now());
        frequencia.setStatus(TipoFrequencia.convertTipoTipoFrequencia("presente"));
        frequencia.setEntrada(frequenciaDTO.horaAtual());
        frequenciaRepository.save(frequencia).getId();

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

        Page<Frequencia> page = datatables.getSearch().isEmpty() ? frequenciaRepository.findAll(datatables.getPageable())
                : frequenciaRepository.searchAll(TipoFrequencia.convertTipoTipoFrequencia(datatables.getSearch()), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    public void delete(Long id) {
        final Frequencia frequencia = frequenciaRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        frequenciaRepository.delete(frequencia);
    }

    protected void ponto(Frequencia frequencia, FrequenciaDTO frequenciaDTO) {
        if (frequencia.getIntervalo() == null || frequencia.getIntervalo().equals("")) {
            frequenciaRepository.updateIntervalo(frequenciaDTO.horaAtual(), frequencia.getId());
            // return frequencia.getId();
        } else if (frequencia.getRetorno() == null || frequencia.getRetorno().equals("")) {
            frequenciaRepository.updateRetorno(frequenciaDTO.horaAtual(), frequencia.getId());
            //return frequencia.getId();
        } else if (frequencia.getSaida() == null || frequencia.getSaida().equals("")) {
            frequenciaRepository.updateSaida(frequenciaDTO.horaAtual(), frequencia.getId());
            //return frequencia.getId();
        }
    }

}

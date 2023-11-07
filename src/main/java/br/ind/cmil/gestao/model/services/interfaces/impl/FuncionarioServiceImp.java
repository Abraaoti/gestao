package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.exceptions.FuncionarioException;
import br.ind.cmil.gestao.model.datatables.Datatables;
import br.ind.cmil.gestao.model.datatables.DatatablesColunas;
import br.ind.cmil.gestao.model.entidades.Funcionario;
import br.ind.cmil.gestao.model.enums.EstadoCivil;
import br.ind.cmil.gestao.model.enums.Genero;
import br.ind.cmil.gestao.model.repositorys.IFuncionarioRepository;
import br.ind.cmil.gestao.model.services.interfaces.CargoService;
import br.ind.cmil.gestao.model.services.interfaces.DepartamentoService;
import br.ind.cmil.gestao.model.services.interfaces.FuncionarioService;
import br.ind.cmil.gestao.model.services.interfaces.LotacaoService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
@Service
public class FuncionarioServiceImp implements FuncionarioService {

    @Autowired
    private LotacaoService lotacaoService;
    @Autowired
    private CargoService cargoService;
    @Autowired
    private DepartamentoService departamentoService;
    @Autowired
    private IFuncionarioRepository funcionarioRepository;
    @Autowired
    private Datatables datatables;

    @Override
    @Transactional(readOnly = true)
    public List<Funcionario> list(Pageable pageable) {

        return funcionarioRepository.searchAll(pageable).stream().map((funcionario) -> new Funcionario()).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new FuncionarioException(String.valueOf(id), "Este id não consta no bd! "));
    }

    @Transactional(readOnly = false)
    public void demitirFuncionario(Long id) {
        Funcionario fu = funcionarioRepository.findById(id).orElseThrow(() -> new FuncionarioException(String.valueOf(id), "Este id não consta no bd! "));
        fu.setDemissao(LocalDate.now());
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void salvar(Funcionario funcionario) {
        funcionario.getId();
        validarAtributos(funcionario);

        if (funcionario.getId() == null) {
            funcionario.setAdmissao(data(funcionario.getAdmissao()));
            funcionario.setGenero(Genero.convertGeneroValue(funcionario.getGenero().getValue()));
            funcionario.setEstado_civil(EstadoCivil.findTipo(funcionario.getEstado_civil().getValue()));
            funcionario.setCargo(cargoService.findByNome(funcionario.getCargo().getNome()));
            funcionario.setDepartamento(departamentoService.findByNome(funcionario.getDepartamento().getNome()));
            funcionario.setLotacao(lotacaoService.findByNome(funcionario.getLotacao().getNome()));

            funcionarioRepository.save(funcionario);
        }

        update(funcionario);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    protected Funcionario update(Funcionario f) {

        Optional<Funcionario> funcionarioId = funcionarioRepository.findById(f.getId());
        if (funcionarioId.isEmpty()) {
            return null;
        }

        var funcionario = funcionarioId.get();

        funcionario.setNome(f.getNome());
        funcionario.setSobrenome(f.getSobrenome());
        funcionario.setNascimento(f.getNascimento());
        funcionario.setCpf(f.getCpf());
        funcionario.setRg(f.getRg());
        funcionario.setMae(f.getMae());
        funcionario.setPai(f.getPai());
        funcionario.setClt(f.getClt());
        funcionario.setGenero(Genero.convertGeneroValue(f.getGenero().getValue()));
        funcionario.setEstado_civil(EstadoCivil.findTipo(f.getEstado_civil().getValue()));
        funcionario.setNaturalidade(f.getNaturalidade());
        funcionario.setAdmissao(data(f.getAdmissao()));
        funcionario.setDemissao(f.getDemissao());
        funcionario.setSalario(f.getSalario());
        funcionario.setCargo(f.getCargo());
        funcionario.setDepartamento(f.getDepartamento());
        funcionario.setLotacao(f.getLotacao());

        funcionario.setId(f.getId());

        return funcionarioRepository.save(funcionario);

    }

    @Override
    public void delete(Long id) {
        funcionarioRepository.delete(funcionarioRepository.findById(id).orElseThrow(() -> new FuncionarioException(String.valueOf(id), "Este id não consta no bd! ")));
    }

    private void validarAtributos(Funcionario f) {
        Optional<Funcionario> funcionario = funcionarioRepository.findByNome(f.getNome());
        if (funcionario.isPresent() && !Objects.equals(funcionario.get().getId(), f.getId())) {
            throw new DataIntegrityViolationException("nome já cadastro no sistema!");
        }
        funcionario = funcionarioRepository.findBySobrenome(f.getSobrenome());
        if (funcionario.isPresent() && !Objects.equals(funcionario.get().getId(), f.getId())) {
            throw new DataIntegrityViolationException("sobrenome já cadastro no sistema!");
        }
        funcionario = funcionarioRepository.findByCpf(f.getCpf());
        if (funcionario.isPresent() && !Objects.equals(funcionario.get().getId(), f.getId())) {
            throw new DataIntegrityViolationException("cep já cadastro no sistema!");
        }
        funcionario = funcionarioRepository.findByRg(f.getRg());
        if (funcionario.isPresent() && !Objects.equals(funcionario.get().getId(), f.getId())) {
            throw new DataIntegrityViolationException("rg já cadastro no sistema!");
        }

    }

    @Override
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.DEPARTAMENTO);
        Page<Funcionario> page = datatables.getSearch().isEmpty() ? funcionarioRepository.findAll(datatables.getPageable())
                : funcionarioRepository.searchAll(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    public long countById() {
        return funcionarioRepository.count();
    }

    protected LocalDate data(LocalDate dto) {
        return (dto == null ? LocalDate.now() : dto);
    }

}

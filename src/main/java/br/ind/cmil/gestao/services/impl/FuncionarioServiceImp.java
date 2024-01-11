package br.ind.cmil.gestao.services.impl;

import br.ind.cmil.gestao.exceptions.FuncionarioException;
import br.ind.cmil.gestao.datatables.Datatables;
import br.ind.cmil.gestao.datatables.DatatablesColunas;
import br.ind.cmil.gestao.model.dto.FuncionarioDTO;
import br.ind.cmil.gestao.domain.Cargo;
import br.ind.cmil.gestao.domain.CentroCusto;
import br.ind.cmil.gestao.domain.Departamento;
import br.ind.cmil.gestao.domain.Funcionario;
import br.ind.cmil.gestao.enums.EstadoCivil;
import br.ind.cmil.gestao.enums.Genero;
import br.ind.cmil.gestao.mappers.FuncionarioMapper;
import br.ind.cmil.gestao.repositorys.CargoRepository;
import br.ind.cmil.gestao.repositorys.CentroCustoRepository;
import br.ind.cmil.gestao.repositorys.DepartamentoRepository;
import br.ind.cmil.gestao.services.FuncionarioService;
import java.util.List;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import br.ind.cmil.gestao.repositorys.FuncionarioRepository;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;

/**
 *
 * @author abraao
 */
@Service
public class FuncionarioServiceImp implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final Datatables datatables;
    private final DepartamentoRepository departamentoRepository;
    private final CargoRepository cargoRepository;
    private final CentroCustoRepository centroCustoRepository;
    private final FuncionarioMapper funcionarioMapper;

    public FuncionarioServiceImp(FuncionarioRepository funcionarioRepository, Datatables datatables, DepartamentoRepository departamentoRepository, CargoRepository cargoRepository, CentroCustoRepository centroCustoRepository, FuncionarioMapper funcionarioMapper) {
        this.funcionarioRepository = funcionarioRepository;
        this.datatables = datatables;
        this.departamentoRepository = departamentoRepository;
        this.cargoRepository = cargoRepository;
        this.centroCustoRepository = centroCustoRepository;
        this.funcionarioMapper = funcionarioMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FuncionarioDTO> list() {
        
        List<Funcionario> funcionarios =funcionarioRepository.findAll(Sort.by("id"));
        return funcionarios.stream().map(funcionarioMapper::toDTO).collect(Collectors.toList());

    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Long salvar(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = funcionarioMapper.toEntity(funcionarioDTO);
        validarAtributos(funcionario);
        if (funcionarioDTO.id() == null) {
            return funcionarioRepository.save(funcionario).getId();
        }

        return null;

    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void update(Long id, FuncionarioDTO f) {

        var funcionario = funcionarioRepository.findById(id).get();
        mapToEntity(funcionario, f);
        funcionarioRepository.save(funcionario);

    }

    @Override
    @Transactional(readOnly = true)
    public FuncionarioDTO buscarFuncionarioPorId(Long id) {

        return funcionarioRepository.findById(id).map(funcionario -> mapToDTO(funcionario)).get();
    }
    @Override
    @Transactional(readOnly = true)
    public FuncionarioDTO buscarFuncionarioPorNome(String nome) {

        return funcionarioRepository.findByNome(nome).map(funcionario -> mapToDTO(funcionario)).get();
    }

    @Transactional(readOnly = false)
    public void demitirFuncionario(Long id) {
        Funcionario fu = funcionarioRepository.findById(id).orElseThrow(() -> new FuncionarioException(String.valueOf(id), "Este id não consta no bd! "));
        fu.setDemissao(LocalDate.now());
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
        datatables.setColunas(DatatablesColunas.FUNCIONARIO);
        Page<Funcionario> page = datatables.getSearch().isEmpty() ? funcionarioRepository.findAll(datatables.getPageable())
                : funcionarioRepository.searchAll(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }
    @Override
    public Map<String, Object> buscarFuncionarioPorCargo(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.MARCAR_PRESENCA);
        Page<Funcionario> page = datatables.getSearch().isEmpty() ? funcionarioRepository.findAll(datatables.getPageable())
                : funcionarioRepository.findByCargo(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    public long countById() {
        return funcionarioRepository.count();
    }

    protected LocalDate data(LocalDate dto) {
        return (dto == null ? LocalDate.now() : dto);
    }

    private FuncionarioDTO mapToDTO(Funcionario funcionario) {

        Long departamento = funcionario.getDepartamento() == null ? null : funcionario.getDepartamento().getId();
        Long cargo = funcionario.getCargo() == null ? null : funcionario.getCargo().getId();
        Long centro = funcionario.getCentro() == null ? null : funcionario.getCentro().getId();
        return new FuncionarioDTO(funcionario.getId(), funcionario.getNome(), funcionario.getSobrenome(), funcionario.getNascimento(), funcionario.getCpf(), funcionario.getRg(), funcionario.getMae(), funcionario.getPai(), funcionario.getClt(), funcionario.getGenero().getValue(), funcionario.getEstado_civil().getValue(), funcionario.getNaturalidade(), funcionario.getAdmissao(), funcionario.getDemissao(), funcionario.getSalario(), cargo, departamento, centro);
    }

    private Funcionario mapToEntity(Funcionario funcionario, FuncionarioDTO funcionarioDTO) {
        funcionario.setNome(funcionarioDTO.nome());
        funcionario.setSobrenome(funcionarioDTO.sobrenome());
        funcionario.setNascimento(funcionarioDTO.nascimento());
        funcionario.setCpf(funcionarioDTO.cpf());
        funcionario.setRg(funcionarioDTO.rg());
        funcionario.setMae(funcionarioDTO.mae());
        funcionario.setPai(funcionarioDTO.pai());
        funcionario.setEstado_civil(EstadoCivil.findTipo(funcionarioDTO.estado_civil()));
        funcionario.setGenero(Genero.convertGeneroValue(funcionarioDTO.genero()));
        funcionario.setSalario(funcionarioDTO.salario());
        funcionario.setNaturalidade(funcionarioDTO.naturalidade());
        LocalDate data = (funcionarioDTO.admissao()) == null ? LocalDate.now() : funcionarioDTO.admissao();
        funcionario.setAdmissao(data);
        funcionario.setDemissao(funcionarioDTO.demissao());
        funcionario.setClt(funcionarioDTO.clt());
        Departamento departamento = funcionarioDTO.departamento() == null ? null : departamentoRepository.findById(funcionarioDTO.departamento())
                .get();
        funcionario.setDepartamento(departamento);
        Cargo cargo = funcionarioDTO.cargo() == null ? null : cargoRepository.findById(funcionarioDTO.cargo()).get();
        funcionario.setCargo(cargo);
        CentroCusto centro = funcionarioDTO.centro() == null ? null : centroCustoRepository.findById(funcionarioDTO.centro())
                .get();
        funcionario.setCentro(centro);
        return funcionario;
    }

   

   

}

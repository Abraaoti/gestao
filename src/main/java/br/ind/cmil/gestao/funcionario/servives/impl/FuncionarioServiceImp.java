package br.ind.cmil.gestao.funcionario.servives.impl;

import br.ind.cmil.gestao.cargo.domain.Cargo;
import br.ind.cmil.gestao.cargo.repository.CargoRepository;
import br.ind.cmil.gestao.exceptions.FuncionarioException;
import br.ind.cmil.gestao.datatables.Datatables;
import br.ind.cmil.gestao.datatables.DatatablesColunas;
import br.ind.cmil.gestao.departamento.domain.Departamento;
import br.ind.cmil.gestao.departamento.respository.DepartamentoRepository;

import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import br.ind.cmil.gestao.funcionario.mapper.FuncionarioMapper;
import br.ind.cmil.gestao.funcionario.model.FuncionarioDTO;
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
import br.ind.cmil.gestao.funcionario.repository.FuncionarioRepository;
import br.ind.cmil.gestao.funcionario.services.FuncionarioService;
import br.ind.cmil.gestao.pessoa.domain.PessoaJuridica;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import br.ind.cmil.gestao.pessoa.repository.PessoaJuridicaRepository;

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
    private final PessoaJuridicaRepository empresaRepository;
    private final FuncionarioMapper funcionarioMapper;

    public FuncionarioServiceImp(FuncionarioRepository funcionarioRepository, Datatables datatables, DepartamentoRepository departamentoRepository, CargoRepository cargoRepository, PessoaJuridicaRepository empresaRepository, FuncionarioMapper funcionarioMapper) {
        this.funcionarioRepository = funcionarioRepository;
        this.datatables = datatables;
        this.departamentoRepository = departamentoRepository;
        this.cargoRepository = cargoRepository;
        this.empresaRepository = empresaRepository;
        this.funcionarioMapper = funcionarioMapper;
    }

   
   

    @Override
    @Transactional(readOnly = true)
    public List<FuncionarioDTO> list() {

        List<Funcionario> funcionarios = funcionarioRepository.findAll(Sort.by("id"));
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
    public void update(Long id, FuncionarioDTO funcionarioDTO) {

        var funcionario = funcionarioRepository.findById(id).get();
        mapToEntity(funcionario, funcionarioDTO);
        funcionarioRepository.save(funcionario);

    }

    @Override
    @Transactional(readOnly = true)
    public FuncionarioDTO buscarFuncionarioPorId(Long id) {
        return funcionarioRepository.findById(id).map(funcionario -> funcionarioMapper.toDTO(funcionario)).get();
    }

    @Override
    @Transactional(readOnly = true)
    public FuncionarioDTO buscarFuncionarioPorNome(String nome) {

        return funcionarioRepository.findByClt(nome).map(funcionario -> funcionarioMapper.toDTO(funcionario)).get();
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
        Optional<Funcionario> funcionario = funcionarioRepository.findByClt(f.getClt());
        if (funcionario.isPresent() && !Objects.equals(funcionario.get().getId(), f.getId())) {
            throw new DataIntegrityViolationException("clt já cadastro no sistema!");
        }
       

    }

    @Override
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.FUNCIONARIO);
        Page<?> page = datatables.getSearch().isEmpty() ? funcionarioRepository.findAll(datatables.getPageable())
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

    private Funcionario mapToEntity(Funcionario funcionario, FuncionarioDTO funcionarioDTO) {
       
        LocalDate data = (funcionarioDTO.admissao()) == null ? LocalDate.now() : funcionarioDTO.admissao();
        funcionario.setAdmissao(data);
        funcionario.setDemissao(funcionarioDTO.demissao());
        funcionario.setClt(funcionarioDTO.clt());
        Departamento departamento = funcionarioDTO.departamento() == null ? null : departamentoRepository.findById(funcionarioDTO.departamento())
                .get();
        funcionario.setDepartamento(departamento);
        Cargo cargo = funcionarioDTO.cargo() == null ? null : cargoRepository.findById(funcionarioDTO.cargo()).get();
        funcionario.setCargo(cargo);
        PessoaJuridica empresa = funcionarioDTO.empresa()== null ? null : empresaRepository.findById(funcionarioDTO.empresa())
                .get();
        funcionario.setEmpresa(empresa);
        return funcionario;
    }


    @Override
    @Transactional(readOnly = true)
    public Set<Funcionario> funcionarioString(Set<String> funcionarosString) {
        Set<Funcionario> funcionarios = new HashSet<>();

        for (String string : funcionarosString) {
            Funcionario funcionario = funcionarioRepository.findByClt(string).get();
            funcionarios.add(funcionario);

        }
        return funcionarios;

    }


}

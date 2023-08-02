package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.exceptions.FuncionarioException;
import br.ind.cmil.gestao.model.dto.FuncionarioDTO;
import br.ind.cmil.gestao.model.dto.PessoaDTO;
import br.ind.cmil.gestao.model.dto.mappers.DepartamentoMapper;
import br.ind.cmil.gestao.model.dto.mappers.FuncionarioMapper;
import br.ind.cmil.gestao.model.dto.mappers.PessoaMapper;
import br.ind.cmil.gestao.model.entidades.Departamento;
import br.ind.cmil.gestao.model.entidades.Funcionario;
import br.ind.cmil.gestao.model.repositorys.IFuncionarioRepository;
import br.ind.cmil.gestao.model.services.interfaces.IDepartamentoService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.ind.cmil.gestao.model.services.interfaces.IFuncionarioService;
import java.util.Objects;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
@Service
public class FuncionarioServiceImp implements IFuncionarioService {

    private final IFuncionarioRepository fr;
    private final PessoaMapper fm;
    private final IDepartamentoService d;
    private final DepartamentoMapper dm;

    public FuncionarioServiceImp(IFuncionarioRepository fr, PessoaMapper fm, IDepartamentoService d, DepartamentoMapper dm) {
        this.fr = fr;
        this.fm = fm;
        this.d = d;
        this.dm = dm;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PessoaDTO> list(Pageable pageable) {
        return fr.searchAll(pageable).stream().map(fm::toDTO).collect(Collectors.toList());
    }
   

    @Override
    @Transactional(readOnly = true)
    public PessoaDTO findById(Long id) {
        return fr.findById(id).map(fm::toDTO).orElseThrow(() -> new FuncionarioException(String.valueOf(id), "Este id não consta no bd! "));
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public PessoaDTO create(FuncionarioDTO f) {
        
        f.getId();
        validarAtributos(f);
        Funcionario funcionario = (Funcionario) fm.toEntity(f);
              
        if (funcionario.getId() == null) {
            Departamento departamento = d.findByNome(f.getDepartamento().nome());
            funcionario.setDepartmento(departamento);

            return fm.toDTO(fr.save(funcionario));
        }

        return update(f);
    }

    protected PessoaDTO update(FuncionarioDTO f) {
        Optional<Funcionario> funcionarioId = fr.findById(f.getId());
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
        funcionario.setPassaporte(f.getPassaporte());
        funcionario.setGenero(fm.convertGeneroValue(f.getGenero()));
        funcionario.setEstado_civil(fm.convertECValue(f.getEstado_civil()));
        funcionario.setNaturalidade(f.getNaturalidade());
        funcionario.setAdmissao(f.getAdmissao());
        funcionario.setMatricula(f.getMatricula());
        funcionario.setDemissao(f.getDemissao());
        funcionario.setSalario(f.getSalario());
        funcionario.setDepartmento(dm.toEntity(f.getDepartamento()));
        funcionario.setMatricula(f.getMatricula());

        funcionario.setId(f.getId());

        return fm.toDTO(fr.save(funcionario));

    }

    @Override
    public void delete(Long id) {
        fr.delete(fr.findById(id).orElseThrow(() -> new FuncionarioException(String.valueOf(id), "Este id não consta no bd! ")));
    }

    private void validarAtributos(FuncionarioDTO f) {
        Optional<Funcionario> funcionario = fr.findByNome(f.getNome());
        if (funcionario.isPresent() && !Objects.equals(funcionario.get().getId(), f.getId())) {
            throw new DataIntegrityViolationException("nome já cadastro no sistema!");
        }
        funcionario = fr.findBySobrenome(f.getSobrenome());
        if (funcionario.isPresent() && !Objects.equals(funcionario.get().getId(), f.getId())) {
            throw new DataIntegrityViolationException("sobrenome já cadastro no sistema!");
        }
        funcionario = fr.findByCpf(f.getCpf());
        if (funcionario.isPresent() && !Objects.equals(funcionario.get().getId(), f.getId())) {
            throw new DataIntegrityViolationException("cep já cadastro no sistema!");
        }
        funcionario = fr.findByRg(f.getRg());
        if (funcionario.isPresent() && !Objects.equals(funcionario.get().getId(), f.getId())) {
            throw new DataIntegrityViolationException("rg já cadastro no sistema!");
        }

    }

}

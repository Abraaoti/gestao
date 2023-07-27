package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.exceptions.FuncionarioException;
import br.ind.cmil.gestao.model.dto.FuncionarioDTO;
import br.ind.cmil.gestao.model.dto.mappers.DepartamentoMapper;
import br.ind.cmil.gestao.model.dto.mappers.FuncionarioMapper;
import br.ind.cmil.gestao.model.entidades.Departamento;
import br.ind.cmil.gestao.model.entidades.Funcionario;
import br.ind.cmil.gestao.model.repositorys.IFuncionarioRepository;
import br.ind.cmil.gestao.model.services.interfaces.IDepartamentoService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.ind.cmil.gestao.model.services.interfaces.IFuncionarioService;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
@Service
public class FuncionarioServiceImp implements IFuncionarioService {

    private final IFuncionarioRepository fr;
    private final FuncionarioMapper fm;
    private final IDepartamentoService d;
    private final DepartamentoMapper dm;

    public FuncionarioServiceImp(IFuncionarioRepository fr, FuncionarioMapper fm, IDepartamentoService d, DepartamentoMapper dm) {
        this.fr = fr;
        this.fm = fm;
        this.d = d;
        this.dm = dm;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FuncionarioDTO> list(Pageable pageable) {
        return fr.searchAll(pageable).stream().map(fm::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public FuncionarioDTO findById(Long id) {
        return fr.findById(id).map(fm::toDTO).orElseThrow(() -> new FuncionarioException(String.valueOf(id), "Este id não consta no bd! "));
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public FuncionarioDTO create(FuncionarioDTO f) {

        Funcionario funcionario = fm.toEntity(f);
        if (funcionario.getId() == null) {

            Departamento departamento = d.findByNome(f.departamento().nome());

            funcionario.setDepartmento(departamento);

            return fm.toDTO(fr.save(funcionario));
        }

        return update(f);
    }

  
    protected FuncionarioDTO update(FuncionarioDTO f) {
        Optional<Funcionario> funcionarioId = fr.findById(f.id());
        if (funcionarioId.isEmpty()) {
            return null;
        }

        Departamento d = dm.toEntity(f.departamento());
        var funcionario = funcionarioId.get();

        funcionario.setNome(f.nome());
        funcionario.setSobrenome(f.sobrenome());
        funcionario.setNascimento(f.nascimento());
        funcionario.setCpf(f.cpf());
        funcionario.setRg(f.rg());
        funcionario.setMae(f.mae());
        funcionario.setPai(f.pai());
        funcionario.setPassaporte(f.passaporte());
        funcionario.setGenero(fm.convertGeneroValue(f.genero()));
        funcionario.setEstado_civil(fm.convertECValue(f.estado_civil()));
        funcionario.setNaturalidade(f.naturalidade());
        funcionario.setAdmissao(f.admissao());
        funcionario.setMatricula(f.matricula());
        funcionario.setDemissao(f.demissao());
        funcionario.setSalario(f.salario());
        funcionario.setDepartmento(d);
        funcionario.setAdmissao(f.admissao());
        funcionario.setMatricula(f.matricula());

        funcionario.setId(f.id());

        return fm.toDTO(fr.save(funcionario));

    }

    @Override
    public void delete(Long id) {
        fr.delete(fr.findById(id).orElseThrow(() -> new FuncionarioException(String.valueOf(id), "Este id não consta no bd! ")));
    }

}

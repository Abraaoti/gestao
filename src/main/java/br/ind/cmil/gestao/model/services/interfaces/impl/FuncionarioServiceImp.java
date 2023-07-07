package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.exceptions.FuncionarioException;
import br.ind.cmil.gestao.model.dto.FuncionarioDTO;
import br.ind.cmil.gestao.model.dto.mappers.FuncionarioMapper;
import br.ind.cmil.gestao.model.repositorys.IFuncionarioRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.ind.cmil.gestao.model.services.interfaces.IFuncionarioService;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
@Service
public class FuncionarioServiceImp implements IFuncionarioService {

    private final IFuncionarioRepository fr;
    private final FuncionarioMapper fm;

    public FuncionarioServiceImp(IFuncionarioRepository fr, FuncionarioMapper fm) {
        this.fr = fr;
        this.fm = fm;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FuncionarioDTO> list(Pageable pageable) {
        return fr.searchAll(pageable).stream().map(fm::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public FuncionarioDTO findById(Long id) {
        return fr.findById(id).map(fm::toDTO).orElseThrow(() -> new FuncionarioException(String.valueOf(id),"Este id não consta no bd! "));
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public FuncionarioDTO create(FuncionarioDTO funcionario) {
        return fm.toDTO(fr.save(fm.toEntity(funcionario)));
    }

    @Override
    @Transactional(readOnly = true)
    public FuncionarioDTO update(Long id, FuncionarioDTO f) {
        return fr.findById(id)
                .map(recordFound -> {
                    recordFound.setNome(f.nome());
                    recordFound.setSobrenome(f.sobrenome());
                    recordFound.setNascimento(f.nascimento());
                    recordFound.setCpf(f.cpf());
                    recordFound.setRg(f.rg());
                    recordFound.setMae(f.mae());
                    recordFound.setPai(f.pai());
                    recordFound.setPassaporte(f.passaporte());
                    recordFound.setGenero(fm.convertGeneroValue(f.genero()));
                    recordFound.setEstado_civil(fm.convertECValue(f.estado_civil()));
                    recordFound.setNaturalidade(f.naturalidade());
                    recordFound.setAdmissao(f.admissao());
                    recordFound.setMatricula(f.matricula());
                    recordFound.setDemissao(f.demissao());
                    recordFound.setSalario(f.salario());
                    recordFound.setId(f.id());
                    return fm.toDTO(fr.save(recordFound));
                }).orElseThrow(() -> new FuncionarioException(String.valueOf(id),"Este id não consta no bd! "));
    }

    @Override
    public void delete(Long id) {
        fr.delete(fr.findById(id).orElseThrow(() -> new FuncionarioException(String.valueOf(id),"Este id não consta no bd! ")));
    }

}

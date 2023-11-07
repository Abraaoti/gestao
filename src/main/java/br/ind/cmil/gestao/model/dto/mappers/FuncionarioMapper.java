package br.ind.cmil.gestao.model.dto.mappers;

import br.ind.cmil.gestao.dto.CargoDTO;
import br.ind.cmil.gestao.dto.DepartamentoDTO;
import br.ind.cmil.gestao.dto.FuncionarioDTO;
import br.ind.cmil.gestao.dto.LotacaoDTO;
import br.ind.cmil.gestao.model.entidades.Cargo;
import br.ind.cmil.gestao.model.entidades.Departamento;
import br.ind.cmil.gestao.model.entidades.Funcionario;
import br.ind.cmil.gestao.model.entidades.Lotacao;
import br.ind.cmil.gestao.model.enums.EstadoCivil;
import br.ind.cmil.gestao.model.enums.Genero;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author abraao
 */

public class FuncionarioMapper {

    @Autowired
    private DepartamentoMapper departamentoMapper;
    @Autowired
    private CargoMapper cargoMapper;
    @Autowired
    private LotacaoMapper lotacaoMapper;

    public FuncionarioDTO toDTO(Funcionario f) {       

        return new FuncionarioDTO(f.getId(), f.getNome(), f.getSobrenome(), f.getNascimento(), f.getCpf(), f.getRg(), f.getMae(), f.getPai(), f.getClt(), f.getGenero().getValue(), f.getEstado_civil().getValue(), f.getNaturalidade(), f.getAdmissao(), f.getDemissao(), f.getSalario(), cargoDTO(f.getCargo()), departamentoDTO(f.getDepartamento()), lotacaoDTO(f.getLotacao()));

    }

    public Funcionario toEntity(FuncionarioDTO dto) {
        if (dto == null) {
            return null;
        }
        Funcionario f = new Funcionario();
        f.setId(dto.id());
        f.setNome(dto.nome());
        f.setSobrenome(dto.sobrenome());
        f.setNascimento(dto.nascimento());
        f.setCpf(dto.cpf());
        f.setRg(dto.rg());
        f.setMae(dto.mae());
        f.setPai(dto.pai());
        f.setClt(dto.clt());
        f.setGenero(Genero.convertGeneroValue(dto.genero()));
        f.setEstado_civil(EstadoCivil.findTipo(dto.estado_civil()));
        f.setNaturalidade(dto.naturalidade());
        LocalDate data = (dto.admissao()) == null ? LocalDate.now() : dto.admissao();
        f.setAdmissao(data);
        f.setDemissao(dto.demissao());
        f.setSalario(dto.salario());

        f.setDepartamento(departamento(dto.departamento()));
        f.setCargo(cargo(dto.cargo()));
        f.setLotacao(lotacao(dto.lotacao()));

        return f;
    }

    private Departamento departamento(DepartamentoDTO dto) {
        return this.departamentoMapper.toEntity(dto);
    }

    private Cargo cargo(CargoDTO dto) {
        return this.cargoMapper.toEntity(dto);
    }

    private Lotacao lotacao(LotacaoDTO dto) {
        return this.lotacaoMapper.toEntity(dto);
    }

    private DepartamentoDTO departamentoDTO(Departamento d) {
        return this.departamentoMapper.toDTO(d);
    }

    private CargoDTO cargoDTO(Cargo c) {
        return this.cargoMapper.toDTO(c);
    }

    private LotacaoDTO lotacaoDTO(Lotacao l) {
        return this.lotacaoMapper.toDTO(l);
    }

}

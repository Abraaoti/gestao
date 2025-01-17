package br.ind.cmil.gestao.funcionario.mapper;

import br.ind.cmil.gestao.cargo.domain.Cargo;
import br.ind.cmil.gestao.cargo.repository.CargoRepository;
import br.ind.cmil.gestao.departamento.domain.Departamento;
import br.ind.cmil.gestao.departamento.respository.DepartamentoRepository;
import br.ind.cmil.gestao.enums.EstadoCivil;
import br.ind.cmil.gestao.enums.Genero;
import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import br.ind.cmil.gestao.funcionario.model.CriarFuncionarioDTO;
import br.ind.cmil.gestao.pessoa.domain.PessoaJuridica;
import br.ind.cmil.gestao.pessoa.repository.PessoaJuridicaRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ti
 */
@Component
public class FuncionarioMapper {


    public CriarFuncionarioDTO toDTO(Funcionario funcionario) {
        Long cargo = (funcionario.getCargo().getId() == null) ? null : funcionario.getCargo().getId();
        Long departamento = (funcionario.getDepartamento().getId() == null) ? null : funcionario.getDepartamento().getId();
        Long empresa = (funcionario.getEmpresa().getId() == null) ? null : funcionario.getEmpresa().getId();
        return new CriarFuncionarioDTO(funcionario.getId(), funcionario.getNome(), funcionario.getSobrenome(), funcionario.getNascimento(), funcionario.getCpf(), funcionario.getRg(), funcionario.getMae(), funcionario.getPai(), funcionario.getGenero().getValue(), funcionario.getEstado_civil().getValue(), funcionario.getNaturalidade(), funcionario.getClt(),  cargo, departamento, empresa);

    }

    public Funcionario toEntity(CriarFuncionarioDTO dto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(dto.id());
        funcionario.setNome(dto.nome());
        funcionario.setSobrenome(dto.sobrenome());
        funcionario.setNascimento(dto.nascimento());
        funcionario.setCpf(dto.cpf());
        funcionario.setRg(dto.rg());
        funcionario.setMae(dto.mae());
        funcionario.setPai(dto.pai());
        funcionario.setClt(dto.clt());
        funcionario.setGenero(Genero.convertGeneroValue(dto.genero()));
        funcionario.setEstado_civil(EstadoCivil.findTipo(dto.estado_civil()));
        funcionario.setNaturalidade(dto.naturalidade());
        LocalDate data = (funcionario.getAdmissao()) == null ? LocalDate.now() : funcionario.getAdmissao();
        funcionario.setAdmissao(data);
        funcionario.setDemissao(null);
        funcionario.setCargo(new Cargo(dto.cargo()));
        funcionario.setDepartamento(new Departamento(dto.departamento()));
        funcionario.setEmpresa(new PessoaJuridica(dto.empresa()));

        return funcionario;
    }

}

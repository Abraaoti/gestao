
package br.ind.cmil.gestao.funcionario.mapper;


import br.ind.cmil.gestao.cargo.domain.Cargo;
import br.ind.cmil.gestao.centro.domain.CentroCusto;
import br.ind.cmil.gestao.departamento.domain.Departamento;
import br.ind.cmil.gestao.enums.EstadoCivil;
import br.ind.cmil.gestao.enums.Genero;
import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import br.ind.cmil.gestao.funcionario.model.FuncionarioDTO;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

/**
 *
 * @author ti
 */
@Component
public class FuncionarioMapper {

    public FuncionarioDTO toDTO(Funcionario funcionario) {
        Long cargo = (funcionario.getCargo().getId() == null) ? null : funcionario.getCargo().getId();
        Long departamento = (funcionario.getDepartamento().getId() == null) ? null : funcionario.getDepartamento().getId();
        Long centroCusto = (funcionario.getCentro().getId() == null) ? null : funcionario.getCentro().getId();

        return new FuncionarioDTO(funcionario.getId(), funcionario.getNome(), funcionario.getSobrenome(), funcionario.getNascimento(), funcionario.getCpf(), funcionario.getRg(), funcionario.getMae(), funcionario.getPai(), funcionario.getClt(), funcionario.getGenero().getValue(), funcionario.getEstado_civil().getValue(), funcionario.getNaturalidade(), funcionario.getAdmissao(), funcionario.getDemissao(), cargo, departamento, centroCusto);

    }

    public Funcionario toEntity(FuncionarioDTO dto) {
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
        LocalDate data = (dto.admissao()) == null ? LocalDate.now() : dto.admissao();
        funcionario.setAdmissao(data);
        funcionario.setDemissao(dto.demissao());
        funcionario.setCargo(new Cargo(dto.cargo()));
        funcionario.setDepartamento(new Departamento(dto.departamento()));
        funcionario.setCentro(new CentroCusto(dto.centro()));

        return funcionario;
    }

   

}

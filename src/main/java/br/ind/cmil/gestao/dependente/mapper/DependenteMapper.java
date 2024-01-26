
package br.ind.cmil.gestao.dependente.mapper;

import br.ind.cmil.gestao.dependente.domain.Dependente;
import br.ind.cmil.gestao.dependente.model.DependenteDTO;
import br.ind.cmil.gestao.enums.EstadoCivil;
import br.ind.cmil.gestao.enums.Genero;
import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import org.springframework.stereotype.Component;

/**
 *
 * @author ti
 */
@Component
public class DependenteMapper {
    public DependenteDTO toDTO(Dependente dependente) {       
       Long funcionario = (dependente.getFuncionario() ==null )? null:dependente.getFuncionario().getId();
       
        return new DependenteDTO(dependente.getId(), dependente.getNome(), dependente.getSobrenome(), dependente.getNascimento(), dependente.getCpf(), dependente.getRg(), dependente.getGenero().getValue(), dependente.getEstado_civil().getValue(), funcionario);
    }
    
    public Dependente toEntity(DependenteDTO dto) {
        if (dto == null) {
            return null;
        } 
        Dependente dependente = new Dependente();
        dependente.setId(dto.id());
        dependente.setId(dto.id());
        dependente.setNome(dto.nome());
        dependente.setSobrenome(dto.sobrenome());
        dependente.setNascimento(dto.nascimento());
        dependente.setCpf(dto.cpf());
        dependente.setRg(dto.rg());
        dependente.setGenero(Genero.convertGeneroValue(dto.genero()));
        dependente.setEstado_civil(EstadoCivil.findTipo(dto.estado_civil()));
        dependente.setFuncionario(new Funcionario(dto.funcionario()));
        return dependente;
    }
    
}

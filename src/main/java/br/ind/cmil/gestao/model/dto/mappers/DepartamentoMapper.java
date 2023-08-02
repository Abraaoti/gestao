package br.ind.cmil.gestao.model.dto.mappers;

import br.ind.cmil.gestao.model.dto.DepartamentoDTO;
import br.ind.cmil.gestao.model.entidades.Departamento;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class DepartamentoMapper {

    public DepartamentoDTO toDTO(Departamento d) {
        if (d == null) {
            return null;
        }
       // List<FuncionarioDTO> funcionarios = d.getFuncionarios().stream()
           //     .map(f ->  new FuncionarioDTO(f.getId(), f.getNome(), f.getSobrenome(), f.getNascimento(), f.getCpf(), f.getRg(), f.getMae(), f.getPai(), f.getPassaporte(), f.getGenero().getValue(), f.getEstado_civil().getValue(), f.getNaturalidade(), f.getAdmissao(), f.getMatricula(), f.getDemissao(),null,f.getTelefones(), f.getSalario() ))
               // .collect(Collectors.toList());
        return new DepartamentoDTO(d.getId(), d.getNome());
    }

    public Departamento toEntity(DepartamentoDTO dto) {
        if (dto == null) {
            return null;
        }
        Departamento d = new Departamento();
        d.setId(dto.id());
        d.setNome(dto.nome());
        return d;
    }

    

   
}

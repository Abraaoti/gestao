package br.ind.cmil.gestao.model.dto.mappers;

import br.ind.cmil.gestao.dto.FuncionarioDTO;
import br.ind.cmil.gestao.dto.TelefoneDTO;
import br.ind.cmil.gestao.model.entidades.Funcionario;
import br.ind.cmil.gestao.model.entidades.Telefone;
import br.ind.cmil.gestao.model.enums.TipoTelefone;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class TelefoneMapper {
    
    public TelefoneDTO toDTO(Telefone t) {
        if (t == null) {
            return null;
        }
        FuncionarioMapper dm = new FuncionarioMapper();
        FuncionarioDTO pessoa = dm.toDTO((Funcionario)t.getPessoa());
        // List<FuncionarioDTO> funcionarios = d.getFuncionarios().stream()
        //     .map(f ->  new FuncionarioDTO(f.getId(), f.getNome(), f.getSobrenome(), f.getNascimento(), f.getCpf(), f.getRg(), f.getMae(), f.getPai(), f.getPassaporte(), f.getGenero().getValue(), f.getEstado_civil().getValue(), f.getNaturalidade(), f.getAdmissao(), f.getMatricula(), f.getDemissao(),null,f.getTelefones(), f.getSalario() ))
        // .collect(Collectors.toList());
        return new TelefoneDTO(t.getId(), t.getNumero(), t.getTipo().getValue(), pessoa);
    }
    
    public Telefone toEntity(TelefoneDTO dto) {
        if (dto == null) {
            return null;
        }
        Telefone t = new Telefone();
        t.setId(dto.id());
        t.setNumero(dto.numero());
        t.setTipo(convertTipoTelefoneValue(dto.tipo()));
        
         FuncionarioMapper dm = new FuncionarioMapper();
       //t.setPessoa(dm.toEntity(dto.pessoa()));
        return t;
    }
    
    public TipoTelefone convertTipoTelefoneValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "pessoal" ->
                TipoTelefone.PESSOAL;
            case "comercial" ->
                TipoTelefone.COMERCIAL;
            case "residencial" ->
                TipoTelefone.RESIDENCIAL;
            default ->
                throw new IllegalArgumentException(" Tipo Telefone invalido " + value);
        };
    }
    
}

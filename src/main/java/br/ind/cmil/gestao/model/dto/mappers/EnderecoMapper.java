package br.ind.cmil.gestao.model.dto.mappers;

import br.ind.cmil.gestao.model.dto.EnderecoDTO;
import br.ind.cmil.gestao.model.dto.FuncionarioDTO;
import br.ind.cmil.gestao.model.entidades.Endereco;
import br.ind.cmil.gestao.model.entidades.Funcionario;
import br.ind.cmil.gestao.model.entidades.Pessoa;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class EnderecoMapper {

    public EnderecoDTO toDTO(Endereco e) {
        if (e == null) {
            return null;
        }
         FuncionarioMapper dm = new FuncionarioMapper();
        FuncionarioDTO pessoa = dm.toDTO((Funcionario) e.getPessoa());
     
        return new EnderecoDTO(e.getId(), e.getUf(), e.getCidade(),e.getBairro(),e.getRua(),e.getCep(),e.getNumero(),e.getComplemento(),pessoa);
    }

    public Endereco toEntity(EnderecoDTO dto) {
        if (dto == null) {
            return null;
        }
        Endereco e = new Endereco();
        e.setId(dto.id());
        e.setUf(dto.uf());
        e.setCidade(dto.cidade());
        e.setBairro(dto.bairro());
        e.setRua(dto.rua());
        e.setCep(dto.cep());
        e.setNumero(dto.numero());
        e.setComplemento(dto.complemento());
        
        return e;
    }

    

   
}

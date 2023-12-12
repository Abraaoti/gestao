package br.ind.cmil.gestao.mappers;

import br.ind.cmil.gestao.model.dto.EnderecoDTO;
import br.ind.cmil.gestao.domain.Endereco;
import br.ind.cmil.gestao.domain.Pessoa;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class EnderecoMapper {

    public EnderecoDTO toDTO(Endereco e) {

        Long pessoa = (e.getPessoa().getId() == null) ? null : e.getPessoa().getId();

        return new EnderecoDTO(e.getId(), e.getUf(), e.getCidade(), e.getBairro(), e.getRua(), e.getCep(), e.getNumero(), e.getComplemento(), pessoa);
    }

    public Endereco toEntity(EnderecoDTO dto) {
        Endereco e = new Endereco();
        e.setUf(dto.uf());
        e.setCidade(dto.cidade());
        e.setBairro(dto.bairro());
        e.setRua(dto.rua());
        e.setCep(dto.cep());
        e.setNumero(dto.numero());
        e.setComplemento(dto.complemento());
        e.setPessoa(new Pessoa(dto.id()));
        return e;
    }

}

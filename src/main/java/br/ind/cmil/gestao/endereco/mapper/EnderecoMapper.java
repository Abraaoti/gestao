package br.ind.cmil.gestao.endereco.mapper;

import br.ind.cmil.gestao.endereco.domain.Endereco;
import br.ind.cmil.gestao.endereco.model.EnderecoDTO;
import br.ind.cmil.gestao.pessoa.domain.Pessoa;
import org.springframework.stereotype.Component;

/**
 *
 * @author ti
 */
@Component
public class EnderecoMapper {

    public EnderecoDTO toDTO(Endereco e) {

        String pessoa = (e.getPessoa().getId() == null) ? null : e.getPessoa().getNome();

        return new EnderecoDTO(e.getId(), e.getUf(), e.getCidade(), e.getBairro(), e.getRua(), e.getCep(), e.getNumero(), e.getComplemento(), pessoa);
    }

    public Endereco toEntity(EnderecoDTO dto) {
        Endereco endereco = new Endereco();
        endereco.setUf(dto.uf());
        endereco.setCidade(dto.cidade());
        endereco.setBairro(dto.bairro());
        endereco.setRua(dto.rua());
        endereco.setCep(dto.cep());
        endereco.setNumero(dto.numero());
        endereco.setComplemento(dto.complemento());
        endereco.setPessoa(new Pessoa(dto.pessoa()));
        return endereco;
    }

}

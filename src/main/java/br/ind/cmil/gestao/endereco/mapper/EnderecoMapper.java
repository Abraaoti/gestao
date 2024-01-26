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

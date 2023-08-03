package br.ind.cmil.gestao.model.dto.mappers;

import br.ind.cmil.gestao.model.dto.PessoaDTO;
import br.ind.cmil.gestao.model.dto.PessoaJuridicaDTO;
import br.ind.cmil.gestao.model.entidades.Pessoa;
import br.ind.cmil.gestao.model.entidades.PessoaJuridica;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class PessoaJuridicaMapper {

    public PessoaDTO toDTO(PessoaJuridica j) {
        if (j == null) {
            return null;
        }

        return new PessoaJuridicaDTO(j.getCnpj(), j.getIe(), j.getIm(),j.getId(), j.getNome(), j.getSobrenome(), j.getNascimento());
    }

    public Pessoa toEntity(PessoaJuridicaDTO dto) {
        if (dto == null) {
            return null;
        }
        PessoaJuridica j = new PessoaJuridica();
        j.setId(dto.getId());
        j.setNome(dto.getNome());
        j.setSobrenome(dto.getSobrenome());
        j.setNascimento(dto.getNascimento());
        j.setCnpj(dto.getCnpj());
        j.setIe(dto.getIe());
        j.setIm(dto.getIm());

        return j;
    }

}

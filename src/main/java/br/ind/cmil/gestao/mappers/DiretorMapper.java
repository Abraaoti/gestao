package br.ind.cmil.gestao.mappers;

import br.ind.cmil.gestao.model.dto.DiretorDTO;
import br.ind.cmil.gestao.domain.Diretor;
import br.ind.cmil.gestao.domain.Usuario;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class DiretorMapper {

    public DiretorDTO toDTO(Diretor diretor) {
        Long usuario = (diretor.getUsuario().getId() == null) ? null : diretor.getUsuario().getId();
        return new DiretorDTO(diretor.getId(), diretor.getNome(), usuario);
    }

    public Diretor toEntity(DiretorDTO dto) {
        Diretor diretor = new Diretor();
        diretor.setNome(dto.nome());
        diretor.setUsuario(new Usuario(dto.id()));
        return diretor;
    }
}

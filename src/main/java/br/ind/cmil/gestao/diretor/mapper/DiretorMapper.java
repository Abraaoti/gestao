package br.ind.cmil.gestao.diretor.mapper;

import br.ind.cmil.gestao.diretor.domain.Diretor;
import br.ind.cmil.gestao.diretor.model.DiretorDTO;
import br.ind.cmil.gestao.usuario.domain.Usuario;
import org.springframework.stereotype.Component;

/**
 *
 * @author ti
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

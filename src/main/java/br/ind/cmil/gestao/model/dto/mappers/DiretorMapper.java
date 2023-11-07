
package br.ind.cmil.gestao.model.dto.mappers;

import br.ind.cmil.gestao.dto.DiretorDTO;
import br.ind.cmil.gestao.dto.UsuarioRequest;
import br.ind.cmil.gestao.model.entidades.Diretor;
import br.ind.cmil.gestao.model.entidades.Usuario;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class DiretorMapper {

    public DiretorDTO toDTO(Diretor diretor) {
        UsuarioMapper um = new UsuarioMapper();
        UsuarioRequest usuario = um.toDTO(diretor.getUsuario());
        return new DiretorDTO(diretor.getId(), diretor.getNome(), usuario);
    }

    public Diretor toEntity(DiretorDTO dto) {

        Diretor diretor = new Diretor();
        diretor.setId(dto.id());       
        diretor.setNome(dto.nome());
        UsuarioMapper um = new UsuarioMapper();
        Usuario usuario = um.toEntity(dto.usuario());
        diretor.setUsuario(usuario);
        // u.setPerfis(perfis(dto.perfis()));
        return diretor;
    }
}

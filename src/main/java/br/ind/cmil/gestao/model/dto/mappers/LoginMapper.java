package br.ind.cmil.gestao.model.dto.mappers;

import br.ind.cmil.gestao.model.dto.LoginDTO;
import br.ind.cmil.gestao.model.entidades.Usuario;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class LoginMapper {

    public LoginDTO toDTO(Usuario u) {
        if (u == null) {
            return null;
        }

        return new LoginDTO(u.getId(), u.getNome(), u.getPassword());
    }

    public Usuario toEntity(LoginDTO dto) {
        if (dto == null) {
            return null;
        }
        Usuario u = new Usuario();
        if (dto.id() != null) {
            u.setId(dto.id());
        }
        u.setNome(dto.emailOUNome());
        u.setPassword(dto.password());
        return u;
    }

}

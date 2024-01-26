package br.ind.cmil.gestao.usuario.mapper;

import br.ind.cmil.gestao.usuario.domain.Usuario;
import br.ind.cmil.gestao.usuario.model.CadastroExternoDTO;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

/**
 *
 * @author ti
 */
@Component
public class CadastroExternoMapper {

    public CadastroExternoDTO toDTO(Usuario u) {

        return new CadastroExternoDTO(u.getId(), u.getNome(), u.getEmail(), u.getPassword());
    }

    public Usuario toEntity(CadastroExternoDTO dto) {

        Usuario u = new Usuario();
        u.setId(dto.id());
        u.setNome(dto.nome());
        u.setEmail(dto.email());
        u.setPassword(dto.password());
        u.setDataCadastro(LocalDateTime.now());
        u.setAtivo(false);
        // u.setPerfis(perfis(dto.perfis()));
        return u;
    }

}

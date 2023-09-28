package br.ind.cmil.gestao.model.dto.mappers;

import br.ind.cmil.gestao.exceptions.ObjectNotFoundException;
import br.ind.cmil.gestao.model.dto.UtenteDTO;
import br.ind.cmil.gestao.model.entidades.Usuario;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class UtenteMapper {

    public UtenteDTO toDTO(Usuario u) {
                
        return new UtenteDTO(u.getId(), u.getNome(), u.getEmail(), u.getPassword());
    }

    public Usuario toEntity(UtenteDTO dto) {

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

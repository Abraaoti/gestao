package br.ind.cmil.gestao.model.dto.mappers;

import br.ind.cmil.gestao.model.dto.PerfilDTO;
import br.ind.cmil.gestao.model.dto.request.RegistrarUsuario;
import br.ind.cmil.gestao.model.entidades.Perfil;
import br.ind.cmil.gestao.model.entidades.Usuario;
import br.ind.cmil.gestao.model.enums.TipoPerfil;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class RegistroUsuarioMapper {

    public RegistrarUsuario toDTO(Usuario u) {
        if (u == null) {
            return null;
        }
        Set<PerfilDTO> perfis = u.getPerfis().stream()
                .map(perfil -> new PerfilDTO(perfil.getTp().getValue()
        )).collect(Collectors.toSet());
        PerfilMapper pm = new PerfilMapper();
        Perfil p = new Perfil();
        for (PerfilDTO perfi : perfis) {
            p = pm.toEntity(perfi);
        }

        return null;// new RegistrarUsuario(u.getNome(), u.getEmail(), u.getPassword());
    }

    public Usuario toEntity(RegistrarUsuario dto) {
        if (dto == null) {
            return null;
        }
        Usuario u = new Usuario();
        u.setNome(dto.nome());
        u.setEmail(dto.email());
        u.setPassword(dto.password());

        return u;
    }

    /**
     * private Set<Perfil> getAtuthorities(Set<PerfilDTO> perfis) { PerfilMapper
     * pm = new PerfilMapper(); Set<Perfil> perf = new HashSet<>(); for
     * (PerfilDTO p : perfis) { Perfil ps = pm.toEntity(p); perf.add(ps); }
     * return perf;
    }
     */
}

package br.ind.cmil.gestao.model.dto.mappers;

import br.ind.cmil.gestao.model.dto.PerfilDTO;
import br.ind.cmil.gestao.model.dto.UsuarioDTO;
import br.ind.cmil.gestao.model.entidades.Perfil;
import br.ind.cmil.gestao.model.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario u) {
        if (u == null) {
            return null;
        }
        List<PerfilDTO> perfis = u.getPerfis().stream()
                .map(perfil -> new PerfilDTO(perfil.getId(),
                 perfil.getTp().getValue()
        )).collect(Collectors.toList());
        return new UsuarioDTO(u.getId(), u.getEmail(), u.getPassword(), u.getDataCadastro(), u.isAtivo(), u.getVerificador(), perfis);
    }

    public Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) {
            return null;
        }
        Usuario u = new Usuario();
        if (dto.id() != null) {
            u.setId(dto.id());
        }

        u.setEmail(dto.email());
        u.setPassword(dto.password());
        u.setVerificador(dto.verificador());
        u.setAtivo(dto.ativo());

        u.setPerfis(getAtuthorities(dto.perfis()));
        return u;
    }

    private List<Perfil> getAtuthorities(List<PerfilDTO> perfis) {
        PerfilMapper pm = new PerfilMapper();
        List<Perfil> perf = new ArrayList<>();
        for (PerfilDTO p : perfis) {
            Perfil ps = pm.toEntity(p);
            perf.add(ps);
        }
        return perf;
    }
}

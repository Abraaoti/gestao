package br.ind.cmil.gestao.model.dto.mappers;

import br.ind.cmil.gestao.exceptions.ObjectNotFoundException;
import br.ind.cmil.gestao.model.dto.PerfilDTO;
import br.ind.cmil.gestao.model.dto.request.RegistrarUsuario;
import br.ind.cmil.gestao.model.entidades.Perfil;
import br.ind.cmil.gestao.model.entidades.Usuario;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toSet;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class UsuarioMapper {

    public RegistrarUsuario toDTO(Usuario u) {
//Set<PerfilDTO> perfis = u.getPerfis().stream().map(dto -> new PerfilDTO(dto.getId(), dto.getTp().getValue())).collect(toSet());
       
        Set<String> roles = u.getPerfis().stream().map(p -> p.getTp().getValue()).collect(Collectors.toSet());

        return new RegistrarUsuario(u.getId(), u.getNome(), u.getEmail(), u.getPassword(), u.getDataCadastro(), u.getUpdatedAt(), u.isAtivo(), u.getVerificador(), roles);
    }

    public Usuario toEntity(RegistrarUsuario dto) {

        Usuario u = new Usuario();
        u.setId(dto.id());
        if (u.getId() != null) {
            throw new ObjectNotFoundException("Usuário já consta no nosso banco de dados!");
        }
        u.setNome(dto.nome());
        u.setEmail(dto.email());
        u.setPassword(dto.password());
        u.setVerificador(dto.verificador());
        u.setDataCadastro(LocalDateTime.now());

        u.setAtivo(false);
        u.setPerfis(getPerfis(dto.perfis()));
        return u;
    }

    private String converter(String p) {

        Perfil pe = new Perfil();
        PerfilMapper pm = new PerfilMapper();
        pe.setTp(pm.convertPerfilValue(p));
        return pe.getTp().getValue();
    }

    private Set<String> perfis(Set<Perfil> perfis) {
        PerfilMapper pm = new PerfilMapper();
        Set<String> s = perfis.stream().map(perfil -> converter(perfil.getTp().getValue())).collect(toSet());
        Set<String> ps = new HashSet<>();
        Set<PerfilDTO> perfisdt = (Set<PerfilDTO>) perfis.stream().map(dto -> new PerfilDTO(dto.getId(), dto.getTp().getValue())).collect(toSet());
       
        for (PerfilDTO perfilDTO : perfisdt) {
            Perfil pe = pm.toEntity(perfilDTO);
            ps.add(converter(pe.getTp().getValue()));
        }

        return ps;
    }
    private Set<Perfil> getPerfis(Set<String> perfis) {
        PerfilMapper pm = new PerfilMapper();
        Set<Perfil> s = perfis.stream().map(perfil -> new Perfil(pm.convertPerfilValue(perfil))).collect(toSet());
       
        return s;
    }

}

package br.ind.cmil.gestao.model.dto.mappers;

import br.ind.cmil.gestao.exceptions.ObjectNotFoundException;
import br.ind.cmil.gestao.model.dto.request.RegistrarUsuario;
import br.ind.cmil.gestao.model.entidades.Perfil;
import br.ind.cmil.gestao.model.entidades.Usuario;
import br.ind.cmil.gestao.model.services.interfaces.IPerfilService;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class UsuarioMapper {

    public RegistrarUsuario toDTO(Usuario u) {
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
        //u.setPerfis(perfis(dto.perfis()));
        return u;
    }

    public Set<Perfil> perfis(Set<String> perfis) {
        PerfilMapper pm = new PerfilMapper();
        Perfil p = new Perfil();
        Set<Perfil> roles = new HashSet<>();
        for (String strPerfi : perfis) {
            p.setTp(pm.convertPerfilValue(strPerfi));
            roles.add(p);
        }

        return roles;
    }

}

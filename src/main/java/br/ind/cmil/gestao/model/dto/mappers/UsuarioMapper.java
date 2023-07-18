package br.ind.cmil.gestao.model.dto.mappers;

import br.ind.cmil.gestao.exceptions.ObjectNotFoundException;
import br.ind.cmil.gestao.model.dto.request.RegistrarUsuario;
import br.ind.cmil.gestao.model.entidades.Perfil;
import br.ind.cmil.gestao.model.entidades.Usuario;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class UsuarioMapper {

    private PerfilMapper pm;

    public RegistrarUsuario toDTO(Usuario u) {

        Set<String> perfis = new HashSet<>();
        for (Perfil perfi : u.getPerfis()) {

            Perfil p = new Perfil();
            p.setTp(pm.convertPerfilValue(perfi.getTp().getValue()));
            perfis.add(p.getTp().getValue());
        }

        return new RegistrarUsuario(u.getId(), u.getNome(), u.getEmail(), u.getPassword(), u.getDataCadastro(), u.getUpdatedAt(), u.isAtivo(), u.getVerificador(), perfis);
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
        return u;
    }

   

}
